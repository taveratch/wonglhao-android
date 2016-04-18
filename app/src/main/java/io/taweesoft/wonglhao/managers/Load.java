package io.taweesoft.wonglhao.managers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.util.Map;
import java.util.Observable;

import io.taweesoft.wonglhao.models.User;
import io.taweesoft.wonglhao.ui.views.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TAWEESOFT on 4/18/16 AD.
 */
public class Load extends Observable{

    private Load(){}
    public static Load newInstance() {
        return new Load();
    }

    public void signIn(Map<String , String> map) {
        APIService apiService = HttpManager.getInstance().getAPIService(APIService.class);
        Call<User> checkLogin = apiService.signin(HttpManager.getInstance().createRequestBody(map));
        checkLogin.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                setChanged();
                notifyObservers(response);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //No connection
                Log.e("onFailed" , t.getMessage());
            }
        });
    }

    public void signUp( User user) {
        APIService apiService = HttpManager.getInstance().getAPIService(APIService.class);
        Call<User> signupCall = apiService.signup(user);
        signupCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                setChanged();
                notifyObservers(response);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //No connection
                Log.e("onFailed" , t.getMessage());
            }
        });
    }
}
