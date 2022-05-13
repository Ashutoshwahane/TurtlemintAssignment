package xyz.cybernerd404.turtlemintassignment.utils

import xyz.cybernerd404.turtlemintassignment.model.ApiResponseItem

interface IssueListener {

    fun issue(apiResponseItem: ApiResponseItem)
}