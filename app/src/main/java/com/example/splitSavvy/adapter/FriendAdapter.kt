package com.example.splitSavvy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.splitSavvy.R
import com.example.splitSavvy.databinding.ItemFriendBinding
import com.example.splitSavvy.model.FriendModel

class FriendAdapter (
    private var friendList: MutableList<FriendModel>
): RecyclerView.Adapter<FriendAdapter.FriendViewHolder>(){
    class FriendViewHolder(
        val binding: ItemFriendBinding
    ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendViewHolder {
        val binding = ItemFriendBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FriendViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FriendViewHolder,
        position: Int
    ) {

        val friend = friendList[position]

        holder.binding.imgFriend.setImageResource(friend.avatar)

        holder.binding.tvFriendName.text=friend.name

        holder.binding.tvFriendInfo.text = friend.email

        if(friend.isSelected){
            holder.binding.btnAddFriend.setBackgroundResource(
                R.drawable.bg_round_icon
            )
            holder.binding.imgAddIcon.setImageResource(
                R.drawable.ic_check
            )
        }
        else{
            holder.binding.btnAddFriend.setBackgroundResource(
                R.drawable.bg_primary_circle
            )
            holder.binding.imgAddIcon.setImageResource(
                R.drawable.ic_add
            )
        }

        holder.binding.btnAddFriend.setOnClickListener{
            friend.isSelected=!friend.isSelected
            notifyItemChanged(position)
        }

    }

    override fun getItemCount(): Int {
        return friendList.size
    }

    fun filterList(filteredList: MutableList<FriendModel>){
        friendList= filteredList
        notifyDataSetChanged()
    }
}