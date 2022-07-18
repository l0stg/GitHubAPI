package com.example.githubapiapplication.MainFragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.githubapiapplication.ItemsGitHub
import com.example.githubapiapplication.NavController
import com.example.githubapiapplication.R
import com.example.githubapiapplication.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()
    private val viewModel by viewModels<MainFragmentViewModel>()
    private var myAdapter: MainAdapter? = null
    private val router: NavController = NavController()
    private var itemsSwipeToRefresh: SwipeRefreshLayout? = null
    private var isLoading = false
    private var recyclerView: RecyclerView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myAdapter = MainAdapter({
            router.routeToDetailFragment(it, this.requireView())
        }, {
            shared(it)
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

    private fun shared(item: ItemsGitHub){
        val intent= Intent()
        intent.action=Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT,item.html_url)
        intent.type="text/plain"
        startActivity(Intent.createChooser(intent,"Share To:"))
    }
}