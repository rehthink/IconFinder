package com.rehthink.iconfinder.utils


import android.app.Activity
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import com.rehthink.iconfinder.BuildConfig
import com.rehthink.iconfinder.R
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
fun View.showSnack(text: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, text, duration).show()
}

fun RecyclerView.setPaging(
    layoutManager: LinearLayoutManager,
    callback: (RecyclerPagination) -> Unit
) {
    val pagination = RecyclerPagination(layoutManager, this)
    pagination.onPaginate(callback)
    addOnScrollListener(pagination)
}

fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url)
        .thumbnail(0.5f)
        .placeholder(R.drawable.img_placeholder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.show(show: Boolean) {
    if (show) visible() else gone()
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.isVisible() = visibility == View.VISIBLE

fun Activity.checkPermissionUtil(
    permission: String,
    sharedPreferences: SharedPreferences,
    listener: (ask: Boolean, permanentlyDenied: Boolean) -> Unit
) {

    //CHECK THIS PERMISSION
    if (ActivityCompat.checkSelfPermission(
            this,
            permission
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                permission
            )
        ) {
            //Ask Permission
            listener(true, false)
        } else if (!sharedPreferences.getBoolean(permission, false)
        ) {
            //Ask Permission First Time
            //and save Asked true in pref
            listener(true, false)
            sharedPreferences.edit().putBoolean(permission, true).apply()

        } else {
            //already asked and not rationale i.e denies with do not ask
            //go to setting
            listener(true, true)
        }
    } else {
        listener(false, false)
    }
}

fun Activity.showToast(text: String, debug: Boolean = false) {
    if (debug) {
        if (BuildConfig.DEBUG) Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        return
    }

    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun EditText.fromEditText(
    bindClear: View? = null,
    onClear: (() -> Unit)? = null
): Observable<String> {

    bindClear?.gone()
    bindClear?.setOnClickListener {
        this.setText("")
        onClear?.invoke()
    }


    val publishSubject = PublishSubject.create<String>()
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(
            s: CharSequence,
            start: Int,
            count: Int,
            after: Int
        ) {
        }

        override fun onTextChanged(
            s: CharSequence,
            start: Int,
            before: Int,
            count: Int
        ) {
        }

        override fun afterTextChanged(s: Editable) {
            publishSubject.onNext(s.toString())
            if (s.isNotEmpty()) {
                bindClear?.visible()
            } else {
                onClear?.invoke()
                bindClear?.gone()
            }
        }
    })

    return publishSubject
}
