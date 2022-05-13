package xyz.cybernerd404.turtlemintassignment.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import xyz.cybernerd404.turtlemintassignment.adapter.MainAdapter
import xyz.cybernerd404.turtlemintassignment.databinding.ActivityMainBinding
import xyz.cybernerd404.turtlemintassignment.model.ApiResponseItem
import xyz.cybernerd404.turtlemintassignment.network.Resouce
import xyz.cybernerd404.turtlemintassignment.utils.IssueListener

class MainActivity : AppCompatActivity(), IssueListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        adapter = MainAdapter(this)

        binding.apply {
            issueRV.layoutManager = LinearLayoutManager(this@MainActivity)
            issueRV.adapter = adapter
        }

        viewModel.apiResponse.observe(this) { apiResponse ->
            when(apiResponse) {
                is Resouce.Success -> {
                    apiResponse.data?.let { issueResponse ->
                        adapter.setIssues(issueResponse)
                    }
                }
            }

        }

    }

    override fun issue(apiResponseItem: ApiResponseItem) {
        val gson = Gson()
        Intent(this, IssueActivity::class.java).apply {
            putExtra("json", gson.toJson(apiResponseItem))
            startActivity(this)
        }
    }
}