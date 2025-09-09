package com.example.pulsepod

import retrofit2.Response

fun <T> Response<T>.isSuccess(): Boolean = this.isSuccessful && this.body() != null
