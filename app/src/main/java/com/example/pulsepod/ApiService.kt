package com.example.pulsepod

import com.example.pulsepod.model.PodcastModel
import com.example.pulsepod.RegisterResponse
import com.example.pulsepod.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("api/register.php")
    fun registerUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confirm_password") confirmPassword: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("api/login.php")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    // ✅ Podcast API
    @GET("api/get_love.php")
    fun getPodcasts(): Call<List<PodcastModel>>
    @GET("api/get_motivation.php")
    fun getMotivationPodcast(): Call<List<PodcastModel>>
    @GET("api/get_philo.php")
    fun getPhilosophyPodcast(): Call<List<PodcastModel>>
    @GET("api/get_fiction.php")
    fun getFictionPodcast(): Call<List<PodcastModel>>
    @GET("api/PopCulture.php")
    fun getPopCulturePodcast(): Call<List<PodcastModel>>

}
