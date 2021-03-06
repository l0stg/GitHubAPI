package com.example.githubapiapplication.MainFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.githubapiapplication.ItemsGitHub
import com.example.githubapiapplication.MainActivity.MainViewModel
import com.example.githubapiapplication.R
import com.example.githubapiapplication.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()
    private val viewModel by viewModel<MainFragmentViewModel>()
    private var myAdapter: MainAdapter? = null
    private var isLoading = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myAdapter = MainAdapter {
            when (it) {
                is Open -> viewModel.routeToDetail(it.item.html_url)
                is Share -> startActivity(viewModel.shared(it.item))
            }
        }
        with(binding) {
            myRecyclerView.layoutManager = LinearLayoutManager(activity)
            myRecyclerView.adapter = myAdapter
            swipeRefresh.setOnRefreshListener {
                viewModel.pullToRefresh()
            }

            binding.myRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val visibleItemCount = layoutManager.childCount
                    val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                    val total = myAdapter!!.itemCount
                    if (!isLoading) {
                        if ((visibleItemCount + pastVisibleItem) >= total) {
                            viewModel.loadMoreData()
                            isLoading = true
                        }
                    }
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
        }

        viewModel.list.observe(viewLifecycleOwner) {
            if (!isLoading) {
                myAdapter!!.set(it)
                binding.swipeRefresh.isRefreshing = false
            } else {
                myAdapter!!.addData(it)
                isLoading = false
            }
        }
    }
}