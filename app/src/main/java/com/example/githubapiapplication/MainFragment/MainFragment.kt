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
import com.example.githubapiapplication.ItemsGitHub
import com.example.githubapiapplication.NavController
import com.example.githubapiapplication.R
import com.example.githubapiapplication.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private val viewModel by viewModels<MainFragmentViewModel>()
    private var myAdapter: MainAdapter? = null
    private val router: NavController = NavController()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        val itemsSwipeToRefresh = binding!!.swipeRefresh
        var isLoading = false

        itemsSwipeToRefresh.setOnRefreshListener {
            viewModel.getAllItemList(0)

        }
        viewModel.list.observe(viewLifecycleOwner) {
            if (!isLoading) {
                myAdapter!!.set(it)
                itemsSwipeToRefresh.isRefreshing = false
            } else {
                myAdapter!!.addData(it)
                isLoading = false
            }
        }

        binding!!.myRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
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


    private fun init(){
        myAdapter = MainAdapter({
            router.routeToDetailFragment(it, this.requireView())
        }, {
           shared(it)
        })
        binding?.apply {
            myRecyclerView.layoutManager = LinearLayoutManager(activity)
            myRecyclerView.adapter = myAdapter
        }
    }

    private fun shared(item: ItemsGitHub){
        val intent= Intent()
        intent.action=Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT,item.html_url)
        intent.type="text/plain"
        startActivity(Intent.createChooser(intent,"Share To:"))
    }
}