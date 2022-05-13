package xyz.cybernerd404.turtlemintassignment.network

import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import xyz.cybernerd404.turtlemintassignment.model.ApiResponse

/** api End point*/
const val getSquareGithubIssues = "repos/square/okhttp/issues"
const val BASE_URL = "https://api.github.com/"

interface GithubApi {

    companion object {
        operator fun invoke(): GithubApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(
                    OkHttpClient.Builder().also { client ->
                        if (BuildConfig.DEBUG) {
                            val logging = HttpLoggingInterceptor()
                            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                            client.addInterceptor(logging)
                        }
                    }.build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubApi::class.java)
        }
    }

    /**
     * Get square github issues
     * */
    @GET(getSquareGithubIssues)
    suspend fun getIssues(): Response<ApiResponse>



}