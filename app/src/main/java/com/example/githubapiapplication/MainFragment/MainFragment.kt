package com.example.githubapiapplication.MainFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.githubapiapplication.ItemsGitHub
import com.example.githubapiapplication.R
import com.example.githubapiapplication.databinding.FragmentMainBinding
import com.github.terrakok.cicerone.Router


class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()
    private val viewModel by viewModels<MainFragmentViewModel>()
    private var myAdapter: MainAdapter? = null
    private var itemsSwipeToRefresh: SwipeRefreshLayout? = null
    private var isLoading = false
    private var recyclerView: RecyclerView? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myAdapter = MainAdapter({
            viewModel.routeToDetail(it.html_url)
        }, {
          startActivity(viewModel.shared(it))
        })

        with(binding){
            itemsSwipeToRefresh = swipeRefresh
            recyclerView = myRecyclerView
            recyclerView?.layoutManager = LinearLayoutManager(activity)
            recyclerView?.adapter = myAdapter
        }

        itemsSwipeToRefresh?.setOnRefreshListener {
            viewModel.getAllItemList(0)
        }

        viewModel.list.observe(viewLifecycleOwner) {
            if (!isLoading) {
                myAdapter!!.set(it)
                itemsSwipeToRefresh?.isRefreshing = false
            } else {
                myAdapter!!.addData(it)
                isLoading = false
            }
        }

        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = myAdapter!!.itemCount
                if (!isLoading) {
                    if ((visibleItemCount + pastVisibleItem) >= total)  {
                        viewModel.getAllItemList(viewModel.list.value!!.last().id!!)
                        isLoading = true
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }
}