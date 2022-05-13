package com.androiddevs.mvvmnewsapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import xyz.cybernerd404.turtlemintassignment.model.ApiResponse
import xyz.cybernerd404.turtlemintassignment.model.ApiResponseItem

@Dao
interface IssueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIssues(apiResponse: ApiResponse): Long

    @Query("SELECT * FROM issueTable")
    fun getAllIssue(): LiveData<List<ApiResponseItem>>


}