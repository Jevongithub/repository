package co.uk.learning.searchtest.exploreFragmentBuild.datasource

import androidx.paging.PageKeyedDataSource
import co.uk.learning.searchtest.exploreFragmentBuild.searching
import co.uk.learning.searchtest.model.Broadcasts
import co.uk.learning.searchtest.model.BroadcastsResponse
import co.uk.learning.searchtest.service.ApiService
import co.uk.learning.searchtest.service.ApiServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ExploreDataSource : PageKeyedDataSource<Int, Broadcasts>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Broadcasts>
    ) {


        // now to see if i can pass the function inside of the class

        val service = ApiServiceBuilder.buildService(ApiService::class.java)
        val call = service.getUsers(searching.string)

        call.enqueue(object : Callback<BroadcastsResponse>{
            override fun onFailure(call: Call<BroadcastsResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<BroadcastsResponse>, response: Response<BroadcastsResponse>) {
                if(response.isSuccessful){
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.users
                    responseItems?.map { it.lastName = "${searching.string}" }

                    responseItems?.let {
                        callback.onResult(responseItems,null, 20)
                    }
                }

            }

        })

    }





    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Broadcasts>) {
       /* val service = ApiServiceBuilder.buildService(ApiService::class.java)
        //val call = service.getUsers(params.key)
        val call = service.search("T")

        call.enqueue(object : Callback<BroadcastsResponse>{
            override fun onFailure(call: Call<BroadcastsResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<BroadcastsResponse>, response: Response<BroadcastsResponse>) {
                if(response.isSuccessful){
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.users

                    val key = params.key + 20

                    responseItems?.let {
                        callback.onResult(responseItems,key)
                    }
                }

            }

        })*/

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Broadcasts>) {
       /* val service = ApiServiceBuilder.buildService(ApiService::class.java)
        // val call = service.getUsers(params.key)
        val call = service.search("T")

        call.enqueue(object : Callback<BroadcastsResponse>{
            override fun onFailure(call: Call<BroadcastsResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<BroadcastsResponse>, response: Response<BroadcastsResponse>) {
                if(response.isSuccessful){
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.users

                    val key = if (params.key >1 )params.key -20 else 0

                    responseItems?.let {
                        callback.onResult(responseItems,key)
                    }
                }

            }

        })*/

    }

    companion object{
        const val PAGE_SIZE = 6
        const val FIRST_PAGE = 20

        const val OFFSET = 1

    }


    }