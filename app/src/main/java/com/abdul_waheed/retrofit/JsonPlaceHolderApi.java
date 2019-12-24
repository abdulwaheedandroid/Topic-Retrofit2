package com.abdul_waheed.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    //relative URL, base URL will be defined somewhere else
    @GET("posts")
    Call<List<Post>> getPosts();
}
