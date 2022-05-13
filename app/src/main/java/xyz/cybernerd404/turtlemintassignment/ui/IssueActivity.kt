package xyz.cybernerd404.turtlemintassignment.ui

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import xyz.cybernerd404.turtlemintassignment.databinding.ActivityIssueBinding
import xyz.cybernerd404.turtlemintassignment.model.ApiResponseItem

class IssueActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIssueBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIssueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gson = Gson()
        val responseObject = gson.fromJson(intent.getStringExtra("json"), ApiResponseItem::class.java)

        binding.apply {
            val date = responseObject.created_at.substring(0, 10)
            userName.text = "username :${responseObject.user.login}"
            Glide.with(this@IssueActivity)
                .load(responseObject.user.avatar_url)
                .into(userIv)

            description.text = "Description :${responseObject.body}"
            dateTV.text = date

        }


    }
}