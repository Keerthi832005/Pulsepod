package com.example.pulsepod

data class RegisterResponse(
    val status: String,
    val message: String,
    val data: UserData?
)

data class UserData(
    val user_id :Int,
    val name: String,
    val email: String
)
