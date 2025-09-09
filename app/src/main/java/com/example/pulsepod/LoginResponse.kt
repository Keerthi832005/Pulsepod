package com.example.pulsepod

data class LoginResponse(
    val status: String,
    val message: String,
    val data: UserData
)