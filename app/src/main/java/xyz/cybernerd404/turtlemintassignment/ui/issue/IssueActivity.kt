package xyz.cybernerd404.turtlemintassignment.ui.issue

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.gson.Gson
import xyz.cybernerd404.turtlemintassignment.adapter.CommentAdapter
import xyz.cybernerd404.turtlemintassignment.databinding.ActivityIssueBinding
import xyz.cybernerd404.turtlemintassignment.model.ApiResponseItem

class IssueActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIssueBinding
    private lateinit var viewModel: IssueViewModel
    private lateinit var adapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIssueBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[IssueViewModel::class.java]
        adapter = CommentAdapter()
        val gson = Gson()
        val responseObject =
            gson.fromJson(intent.getStringExtra("json"), ApiResponseItem::class.java)

        binding.apply {
            val date = responseObject.created_at.substring(0, 10)
            userName.text = "username :${responseObject.user.login}"
            Glide.with(this@IssueActivity)
                .load(responseObject.user.avatar_url)
                .into(userIv)

            description.text = "Description :${responseObject.body}"
            dateTV.text = date

            commentRV.layoutManager = LinearLayoutManager(this@IssueActivity)
            commentRV.adapter = adapter
        }

        viewModel.getComment(responseObject.number.toString())
        viewModel.apiResponse.observe(this) { comments ->
            comments.data?.let {
                adapter.setComments(it)
            }
        }

    }
}