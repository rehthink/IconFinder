package com.rehthink.iconfinder.ui.category

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.rehthink.iconfinder.R
import com.rehthink.iconfinder.base.BaseFragment
import com.rehthink.iconfinder.databinding.FragmentCategoryBinding
import com.rehthink.iconfinder.factory.ViewModelProviderFactory
import com.rehthink.iconfinder.utils.RecyclerPagination
import com.rehthink.iconfinder.utils.setPaging
import com.rehthink.iconfinder.utils.show
import javax.inject.Inject


class CategoryFragment : BaseFragment<CategoryViewModel, FragmentCategoryBinding>() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var navController: NavController
    private lateinit var paging: RecyclerPagination

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setToolbar()
        setData()
        viewModel.loadCategory(initialCount = 25)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_search) {
            navController.navigate(R.id.action_categoryFragment_to_searchFragment)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCategoryBinding = FragmentCategoryBinding.inflate(inflater, container, false)

    override fun getViewModel(): Class<CategoryViewModel> = CategoryViewModel::class.java

    override fun getViewModelFactory(): ViewModelProviderFactory = viewModelProviderFactory

    override fun observeData() {
        super.observeData()
        viewModel.categoryData.observe(viewLifecycleOwner, Observer {
            if(this::paging.isInitialized) {
                paging.setShouldLoadMore(viewModel.hasMoreData)
            }
            categoryAdapter.submitList(it)
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            binding.progressCircular.show(it)
        })
    }

    private fun setToolbar() {
        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
        }
    }

    private fun setData() {
        binding.rcvCategory.apply {
            layoutManager = LinearLayoutManager(context).also {
                setPaging(it) { p ->
                    paging = p
                    viewModel.loadCategory()
                }
            }

            adapter = CategoryAdapter {
                com.rehthink.iconfinder.ui.category.CategoryFragmentDirections.actionCategoryFragmentToIconSetFragment(it.identifier)
                    .apply {
                        navController.navigate(this)
                    }
            }.also { categoryAdapter = it }
        }
    }
}