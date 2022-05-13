package xyz.cybernerd404.turtlemintassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import xyz.cybernerd404.turtlemintassignment.databinding.ItemCommentLayoutBinding
import xyz.cybernerd404.turtlemintassignment.databinding.ItemIssueLayoutBinding
import xyz.cybernerd404.turtlemintassignment.model.ApiResponseItem
import xyz.cybernerd404.turtlemintassignment.model.CommentItem
import xyz.cybernerd404.turtlemintassignment.utils.IssueListener

class CommentAdapter :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    var list: List<CommentItem> = arrayListOf()

    fun setComments(response: List<CommentItem>) {
        this.list = response
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(ItemCommentLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.apply {
            Glide.with(this.root)
                .load(list[position].user.avatar_url)
                .into(this.userIv)

            this.userName.text = list[position].user.login
            this.comment.text = list[position].body
        }


     /*   holder.binding.root.setOnClickListener {
            listener.issue(list[position])
        }*/


    }


    class ViewHolder(val binding: ItemCommentLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)


}