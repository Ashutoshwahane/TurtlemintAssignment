package xyz.cybernerd404.turtlemintassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import xyz.cybernerd404.turtlemintassignment.databinding.ItemIssueLayoutBinding
import xyz.cybernerd404.turtlemintassignment.model.ApiResponseItem
import xyz.cybernerd404.turtlemintassignment.utils.IssueListener

class MainAdapter(val listener: IssueListener) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    var list: List<ApiResponseItem> = arrayListOf()

    fun setIssues(response: List<ApiResponseItem>) {
        this.list = response
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(ItemIssueLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.description.text = list[position].title
        holder.binding.issueNumber.text = "#${list[position].number}"

        holder.binding.root.setOnClickListener {
            listener.issue(list[position])
        }


    }


    class ViewHolder(val binding: ItemIssueLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)


}