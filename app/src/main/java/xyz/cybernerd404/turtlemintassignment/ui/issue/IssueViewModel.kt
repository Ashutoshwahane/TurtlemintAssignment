package xyz.cybernerd404.turtlemintassignment.ui.issue

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import xyz.cybernerd404.turtlemintassignment.model.ApiResponse
import xyz.cybernerd404.turtlemintassignment.model.Comment
import xyz.cybernerd404.turtlemintassignment.network.GithubApi
import xyz.cybernerd404.turtlemintassignment.network.Resouce

class IssueViewModel(application: Application): AndroidViewModel(application) {

    val apiResponse: MutableLiveData<Resouce<Comment>> = MutableLiveData()


    fun getComment(id: String) = viewModelScope.launch {
        val response = GithubApi().getComment(id)
        apiResponse.value = handleApiResponse(response)
    }




    private fun handleApiResponse(response: Response<Comment>): Resouce<Comment>{
        if (response.isSuccessful){
            response.body()?.let {
                return Resouce.Success(it)
            }
        }
        return Resouce.Error(response.message())
    }
}