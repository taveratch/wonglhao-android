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

    public void signIn(final Context context , Map<String , String> map) {
        APIService apiService = HttpManager.getInstance().getAPIService(APIService.class);
        Call<User> checkLogin = apiService.signin(HttpManager.getInstance().createRequestBody(map));
        checkLogin.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    // TODO: 4/13/16 AD  Save into SharedPreference
                    setChanged();
                    notifyObservers(response.body());
                }else{
                    try {
                        Utility.showToastDialog(context,response.errorBody().string(), false).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.e("errors" , response.raw().toString());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //Login failed
                Utility.showToastDialog(context, "Error" , false).show();
            }
        });
    }
}
