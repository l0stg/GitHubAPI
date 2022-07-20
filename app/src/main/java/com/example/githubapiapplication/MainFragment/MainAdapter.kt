package com.example.githubapiapplication.MainFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapiapplication.ItemsGitHub
import com.example.githubapiapplication.R
import com.example.githubapiapplication.databinding.ItemRecyclerViewBinding
import com.example.githubapiapplication.MainFragment.share as share

sealed class Click()
class share(val item: ItemsGitHub): Click()
class open(val item: ItemsGitHub): Click()

class MainAdapter(private val ClickListener: (click: String, it: ItemsGitHub) -> Unit): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val itemList: MutableList<ItemsGitHub> = mutableListOf()

    fun set(newList: List<ItemsGitHub>){
        this.itemList.clear()
        this.itemList.addAll(newList.toMutableList())
        notifyDataSetChanged()
    }

    fun addData(newList: List<ItemsGitHub>){
        this.itemList.addAll(newList)
        notifyDataSetChanged()
    }

    class MainViewHolder(private val itemBinding: ItemRecyclerViewBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: ItemsGitHub, ClickListener: (Click: String, it: ItemsGitHub) -> Unit){
            itemBinding.apply{
                tvDescription.text = item.description
                tvIdItem.text = item.id.toString()
                tvNameMain.text = item.name
                tvOwnerLogin.text = item.owner.login
                Glide.with(ivPerson.context)
                    .load(item.owner.avatar_url)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(ivPerson)
                btnShare.setOnClickListener{
                    ClickListener("share", item)//переделать на sealed class
                }
                root.setOnClickListener {
                    ClickListener("open", item)//переделать на sealed class
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemBinding = ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item: ItemsGitHub = itemList[position]
        holder.bind(item, ClickListener)
    }

    override fun getItemCount(): Int = itemList.size

}