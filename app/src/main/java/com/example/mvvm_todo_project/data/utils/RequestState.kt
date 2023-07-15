package com.example.mvvm_todo_project.data.utils

sealed class RequestState<out T> {
    object Idle : RequestState<Nothing>()
    object Loading : RequestState<Nothing>()
    data class Success<T>(val data: T) : RequestState<T>()
    data class Error(val error: Throwable) : RequestState<Nothing>()
}
// list screen uses two composables to show its contents when list is empty it shows a sad face and when
// there is content it shows the other composable , but when there is content it shows the sad face first because we have
// have given an emptylist value to all_tasks in view model , and it takes a few milliseconds to get the value , we can observe
// the state of data with this sealed class