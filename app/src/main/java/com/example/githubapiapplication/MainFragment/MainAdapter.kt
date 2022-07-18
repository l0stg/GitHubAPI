package com.example.githubapiapplication.MainFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapiapplication.ItemsGitHub
import com.example.githubapiapplication.R
import com.example.githubapiapplication.databinding.ItemRecyclerViewBinding

class MainAdapter(private val itemClickListener: (ItemsGitHub)-> Unit, private val shareClickListener: (ItemsGitHub) -> Unit ): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var itemList: MutableList<ItemsGitHub> = mutableListOf()

    fun set(newList: List<ItemsGitHub>){
        this.itemList = newList.toMutableList()
        notifyDataSetChanged()
    }

    fun addData(newList: List<ItemsGitHub>){
        this.itemList.addAll(newList)
        notifyDataSetChanged()
    }

    class MainViewHolder(private val itemBinding: ItemRecyclerViewBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: ItemsGitHub, shareClickListener: (ItemsGitHub) -> Unit, itemClickListener: (ItemsGitHub) -> Unit){
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
                    shareClickListener(item)
                }
                root.setOnClickListener {
                    itemClickListener(item)
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
        holder.bind(item, shareClickListener, itemClickListener)
    }

    override fun getItemCount(): Int = itemList.size

}