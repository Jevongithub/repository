package co.uk.learning.searchtest.service

import co.uk.learning.searchtest.model.BroadcastsResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("users")
    fun getUsers(@Query("page") page:Int):Call<BroadcastsResponse>

    @FormUrlEncoded
    @POST("SearchAPI.php")
    fun search (
        @Field("searching") distinguishing: String
    ):Call<BroadcastsResponse>

}