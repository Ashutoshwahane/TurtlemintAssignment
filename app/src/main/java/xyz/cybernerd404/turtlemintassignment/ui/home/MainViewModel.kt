package xyz.cybernerd404.turtlemintassignment.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import xyz.cybernerd404.turtlemintassignment.model.ApiResponse
import xyz.cybernerd404.turtlemintassignment.network.GithubApi
import xyz.cybernerd404.turtlemintassignment.network.Resouce

class MainViewModel(application: Application): AndroidViewModel(application) {

    val apiResponse: MutableLiveData<Resouce<ApiResponse>> = MutableLiveData()
    init {
        getSquareIssue()
    }

    fun getSquareIssue() = viewModelScope.launch {
        val response = GithubApi().getIssues()
        apiResponse.value = handleApiResponse(response)
    }




    private fun handleApiResponse(response: Response<ApiResponse>): Resouce<ApiResponse>{
        if (response.isSuccessful){
            response.body()?.let {
                return Resouce.Success(it)
            }
        }
        return Resouce.Error(response.message())
    }
}