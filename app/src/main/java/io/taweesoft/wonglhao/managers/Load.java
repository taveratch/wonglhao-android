package io.taweesoft.wonglhao.managers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import io.taweesoft.wonglhao.models.Bar;
import io.taweesoft.wonglhao.models.Element;
import io.taweesoft.wonglhao.models.Promotion;
import io.taweesoft.wonglhao.models.User;
import io.taweesoft.wonglhao.ui.adapters.BarAdapter;
import io.taweesoft.wonglhao.ui.adapters.PromotionAdapter;
import io.taweesoft.wonglhao.ui.adapters.SearchBarAdapter;
import io.taweesoft.wonglhao.ui.views.MainActivity;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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

    public void getBar(Map<String ,String> map) {
        APIService apiService = HttpManager.getInstance().getAPIService(APIService.class);
        Call<Element> getBarCall = apiService.getBar(HttpManager.getInstance().createRequestBody(map));
        getBarCall.enqueue(new Callback<Element>() {
            @Override
            public void onResponse(Call<Element> call, Response<Element> response) {
               setChanged();
                notifyObservers(response);
            }

            @Override
            public void onFailure(Call<Element> call, Throwable t) {
                //No connection
                Log.e("onFailed" , t.getMessage());
            }
        });
    }

    public void getAllBar(){
        APIService apiService = HttpManager.getInstance().getAPIService(APIService.class);
        Call<Element> getAllBarCall = apiService.getAllBar(-1);
        getAllBarCall.enqueue(new Callback<Element>() {
            @Override
            public void onResponse(Call<Element> call, Response<Element> response) {
                setChanged();
                notifyObservers(response);
            }

            @Override
            public void onFailure(Call<Element> call, Throwable t) {
                //No connection
                Log.e("error" , t.getMessage());
            }
        });
    }

    public void getAllPromotion(){
        APIService apiService = HttpManager.getInstance().getAPIService(APIService.class);
        Call<Element> promotionsCall = apiService.getAllPromotions(-1);
        promotionsCall.enqueue(new Callback<Element>() {
            @Override
            public void onResponse(Call<Element> call, Response<Element> response) {
                setChanged();
                notifyObservers(response);
            }
            @Override
            public void onFailure(Call<Element> call, Throwable t) {
                //No connection
                Log.e("onFailed" , t.getMessage());
            }
        });
    }

    public void searchBar(Map<String , String> map){
        RequestBody requestBody = HttpManager.getInstance().createRequestBody(map);
        APIService apiService = HttpManager.getInstance().getAPIService(APIService.class);
        Call<Element> searchBarCall = apiService.searchBar(requestBody);
        searchBarCall.enqueue(new Callback<Element>() {
            @Override
            public void onResponse(Call<Element> call, Response<Element> response) {
                setChanged();
                notifyObservers(response);
            }

            @Override
            public void onFailure(Call<Element> call, Throwable t) {
                // TODO: 4/16/16 AD Handle error
                Log.e("onFailed" , t.getMessage());
            }
        });
    }
}
