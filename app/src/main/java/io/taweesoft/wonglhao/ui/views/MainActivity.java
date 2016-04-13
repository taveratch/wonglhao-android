package io.taweesoft.wonglhao.ui.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.taweesoft.wonglhao.R;
import io.taweesoft.wonglhao.managers.APIService;
import io.taweesoft.wonglhao.managers.HttpManager;
import io.taweesoft.wonglhao.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        APIService apiService = HttpManager.getInstance().getAPIService(APIService.class);
        Call<User> getUser = apiService.getUser("taweesoft","1234");
        getUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    User user = response.body();
                    Log.e("email",user.getEmail());
                }else{
                    // TODO: Handle errors
                    Log.e("errors" , response.raw().toString());
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("CCCC",t.getMessage());
            }
        });

        User user = new User();
        user.setUsername("kanoon100");
        user.setPassword("12345");
        user.setEmail("kanoon100@kanoon.com");
        user.setFirstname("Kanoon1");
        user.setLastname("Chaiman1");
        user.setGender("male");
        Call<User> signup = apiService.signup(user);
        signup.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    User user1 = response.body();
                    Log.e("username" , user1.getUsername());
                }else{
                    // TODO: Handle errors
                    Log.e("errors" , response.raw().toString());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("errors" , t.getMessage());
            }
        });
    }
}
