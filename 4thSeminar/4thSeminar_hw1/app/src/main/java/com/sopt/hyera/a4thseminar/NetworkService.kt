package com.sopt.hyera.a4thseminar

import retrofit2.Call
import com.sopt.hyera.a4thseminar.get.GetBoardResponse
import com.sopt.hyera.a4thseminar.post.PostBoardResponse
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface NetworkService {
    @Multipart
    @POST("board")
    fun postBoard(
            @Part boardImg : MultipartBody.Part?,
            @Part("user_id") id : RequestBody,
            @Part("board_title") title: RequestBody,
            @Part("board_content") content : RequestBody
            ) : Call<PostBoardResponse>

    @GET("board")
    fun getContent() : Call<GetBoardResponse>
}