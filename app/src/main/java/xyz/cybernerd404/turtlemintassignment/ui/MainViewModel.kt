package xyz.cybernerd404.turtlemintassignment.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.mvvmnewsapp.db.MainDatabase
import kotlinx.coroutines.launch
import retrofit2.Response
import xyz.cybernerd404.turtlemintassignment.model.ApiResponse
import xyz.cybernerd404.turtlemintassignment.network.GithubApi
import xyz.cybernerd404.turtlemintassignment.network.Resouce

class MainViewModel(application: Application): AndroidViewModel(application) {

    val apiResponse: MutableLiveData<Resouce<ApiResponse>> = MutableLiveData()
    val database = MainDatabase.invoke(application)
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