package com.rehthink.iconfinder.ui.icons

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.rehthink.iconfinder.base.BaseFragment
import com.rehthink.iconfinder.databinding.FragmentIconsBinding
import com.rehthink.iconfinder.factory.ViewModelProviderFactory
import com.rehthink.iconfinder.ui.FullScreenViewActivity
import com.rehthink.iconfinder.utils.Constants
import com.rehthink.iconfinder.utils.RecyclerPagination
import com.rehthink.iconfinder.utils.setPaging
import com.rehthink.iconfinder.utils.show
import java.util.*
import javax.inject.Inject


class IconsFragment : BaseFragment<IconsViewModel, FragmentIconsBinding>() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var paging: RecyclerPagination
    private lateinit var iconAdapter: IconAdapter
    private lateinit var category: String
    var iconSetId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        com.rehthink.iconfinder.ui.icons.IconsFragmentArgs.fromBundle(requireArguments()).also {
            this.iconSetId = it.iconSetId
            this.category = it.category
        }

        setToolbar()
        setData()
        viewModel.loadIcons(iconSetId, initialCount = 25)
    }


    override fun getViewModel(): Class<IconsViewModel> = IconsViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentIconsBinding = FragmentIconsBinding.inflate(inflater, container, false)

    override fun getViewModelFactory(): ViewModelProviderFactory = viewModelProviderFactory

    override fun observeData() {
        super.observeData()
        viewModel.iconsData.observe(viewLifecycleOwner, Observer {
            if (this::paging.isInitialized) {
                paging.setShouldLoadMore(viewModel.hasMoreData)
            }
            iconAdapter.submitList(it)
            binding.tvNoData.show(it.isEmpty())
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            binding.progressCircular.show(it)
        })
    }

    private fun setToolbar() {
        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                title = category.capitalize()
            }
        }
    }

    private fun setData() {
        binding.rcvIconSet.apply {
            layoutManager = GridLayoutManager(context, 3).also {
                setPaging(it) { p ->
                    paging = p
                    viewModel.loadIcons(iconSetId)
                }
            }

            adapter = IconAdapter { icon, iv ->

                val activityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        requireActivity(),
                        iv,
                        "icon"
                    )
                val intent = Intent(
                    requireContext(),
                    FullScreenViewActivity::class.java
                )
                intent.putParcelableArrayListExtra(
                    Constants.KEY_RASTER_SIZE,
                    icon.raster_sizes as ArrayList<out Parcelable>
                )
                intent.putExtra(Constants.KEY_CATEGORY, category)

                startActivity(intent, activityOptionsCompat.toBundle())

            }.also { iconAdapter = it }
        }
    }
}