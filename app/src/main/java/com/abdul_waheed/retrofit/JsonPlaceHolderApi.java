package com.abdul_waheed.retrofit;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {

    //relative URL, base URL will be defined somewhere else
    /*@GET("posts")
    Call<List<Post>> getPosts();*/

    @GET("posts")
    Call<List<Post>> getPosts(
            @Query("userId") int userId,
            @Query("_sort") String sort,
            @Query("_order") String order
    );

    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String, String> parameters);


    /*@GET("posts/2/comments")
    Call<List<Commets>> getComments();*/


    //{} ==> replacement block
    @GET("posts/{id}/comments")
    Call<List<Commets>> getComments(@Path("id") int postId);

    @GET
    Call<List<Commets>> getComments(@Url String url);
}
