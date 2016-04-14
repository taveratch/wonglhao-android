package io.taweesoft.wonglhao.ui.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.taweesoft.wonglhao.R;
import io.taweesoft.wonglhao.managers.APIService;
import io.taweesoft.wonglhao.managers.DataStorage;
import io.taweesoft.wonglhao.managers.HttpManager;
import io.taweesoft.wonglhao.managers.Utility;
import io.taweesoft.wonglhao.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.etUsername) EditText etUsername;
    @Bind(R.id.etPassword) EditText etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSignin)
    public void signin() {
        String username = etUsername.getText().toString().trim().toLowerCase();
        String password = etPassword.getText().toString().trim();
        Map<String,String> map = new HashMap<>();
        map.put("username" , username);
        map.put("password" , password);
        APIService apiService = HttpManager.getInstance().getAPIService(APIService.class);
        Call<User> checkLogin = apiService.signin(HttpManager.getInstance().createRequestBody(map));
        checkLogin.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    // TODO: 4/13/16 AD  Save into SharedPreference
                    User user = response.body();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    DataStorage.user = user;
                    startActivity(intent);
                }else{
                    try {
                        Utility.showToastDialog(LoginActivity.this,response.errorBody().string(), false).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.e("errors" , response.raw().toString());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //Login failed
                Utility.showToastDialog(LoginActivity.this, "Error" , false).show();
            }
        });
    }

    @OnClick(R.id.btnSignup)
    public void signup() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

}
