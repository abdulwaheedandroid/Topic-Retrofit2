package com.abdul_waheed.retrofit;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tv_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

       // getPosts();
        getComments();
    }

    private void getComments() {
        Call<List<Commets>> call = jsonPlaceHolderApi.getComments("posts/2/comments");
        call.enqueue(new Callback<List<Commets>>() {
            @Override
            public void onResponse(Call<List<Commets>> call, Response<List<Commets>> response) {
                if (!response.isSuccessful()){
                    tvResult.setText("Code : " + response.code());
                    return;
                }

                List<Commets> commetsList = response.body();
                for (Commets commets : commetsList) {
                    String content = "";
                    content += "ID: " + commets.getId() + "\n";
                    content += "User ID " + commets.getUserId()+ "\n" ;
                    content += "Post ID " + commets.getPostId()+ "\n" ;
                    content += "Title " + commets.getTitle()+ "\n" ;
                    content += "Body " + commets.getText()+ "\n\n" ;

                    tvResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Commets>> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });
    }

    private void getPosts() {

        Map<String, String> param = new HashMap<>();
        param.put("userId", "1");
        param.put("_sort", "id");
        param.put("_order", "desc");

        //Call<List<Post>> call = jsonPlaceHolderApi.getPosts(4, "id", "desc");
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(param);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    tvResult.setText("Code : " + response.code());
                    return;
                }

                List<Post> posts = response.body();
                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID " + post.getUserId()+ "\n" ;
                    content += "Title " + post.getTitle()+ "\n" ;
                    content += "Body " + post.getText()+ "\n\n" ;

                    tvResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });
    }
}
