package com.rehthink.iconfinder.base

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.Call
import retrofit2.Response
import com.rehthink.iconfinder.network.Resource

abstract class BaseRepository {

    val TAG = "BaseRepository"

    companion object ErrorCode {
        const val SOMETHING_WENT_WRONG = -1
        const val UNKNOWN_ERROR = -2

        fun handleError(o: Any): Resource.Error {

            Log.e("handleError", "Error: $o")

            when (o) {
                is Throwable -> return createError(
                    o.message ?: "Something went wrong",
                    SOMETHING_WENT_WRONG
                )
                is Response<*> -> return createError(
                    o.errorBody()?.string()
                        ?:  "Something went wrong",
                    o.code()
                )
            }

            return createError("Unknown Error", UNKNOWN_ERROR)
        }

        private fun createError(errorMsg: String, errorCode: Int): Resource.Error {
            return Resource.Error(errorMsg, errorCode)
        }
    }


    suspend fun <T : Data, Data> Call<T>.asyncExecute(): Resource<Data> {
        return GlobalScope.async(Dispatchers.IO) {
            try {
                val response = execute()
                if (response.isSuccessful && response.body() != null)
                    return@async Resource.Success(response.body() as Data)
                else return@async handleError(response)


            } catch (e: Exception) {
                return@async handleError(e)
            }
        }.await()
    }
}