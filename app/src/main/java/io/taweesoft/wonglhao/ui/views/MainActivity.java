package io.taweesoft.wonglhao.ui.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.taweesoft.wonglhao.R;
import io.taweesoft.wonglhao.managers.APIService;
import io.taweesoft.wonglhao.managers.HttpManager;
import io.taweesoft.wonglhao.models.User;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        APIService apiService = HttpManager.getInstance().getAPIService(APIService.class);
        Map<String,String> map = new HashMap<>();
        map.put("username" , "taweesoft");
        map.put("password" , "1234");
        Call<User> getUser = apiService.getUser(HttpManager.getInstance().createRequestBody(map));
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
        user.setUsername("kanoon101");
        user.setPassword("12345");
        user.setEmail("kanoon101@kanoon.com");
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
                    Log.e("errors1" , response.raw().toString());
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("errors2" , "User already existed");
            }
        });
    }
}
