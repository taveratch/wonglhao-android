package io.taweesoft.wonglhao.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.taweesoft.wonglhao.R;
import io.taweesoft.wonglhao.managers.APIService;
import io.taweesoft.wonglhao.managers.HttpManager;
import io.taweesoft.wonglhao.managers.MyObservable;
import io.taweesoft.wonglhao.managers.Utility;
import io.taweesoft.wonglhao.models.Bar;
import io.taweesoft.wonglhao.models.Element;
import io.taweesoft.wonglhao.models.Observable;
import io.taweesoft.wonglhao.ui.adapters.SearchBarAdapter;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TAWEESOFT on 4/14/16 AD.
 */
public class SearchFragment extends Fragment implements MyObservable{

    private Observable observable = new Observable();

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.rv) RecyclerView rv;
    @Bind(R.id.etName) EditText etName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.search_fragment_layout,container, false);
        ButterKnife.bind(this,v);
        initComponents();
        return v;
    }

    public void initComponents() {
        toolbar.setTitle(getString(R.string.search));
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                observable.setChanged();
                observable.notifyObservers();
            }
        });
        RecyclerView.LayoutManager llm = new LinearLayoutManager(this.getContext());
        rv.setLayoutManager(llm);
    }

    @Override
    public void addObserver(Observer observer) {
        observable.addObserver(observer);
    }

    @OnClick(R.id.btnSearch)
    public void search() {
        String word = etName.getText().toString();
        if(word.length() == 0 ) return;
        APIService apiService = HttpManager.getInstance().getAPIService(APIService.class);
        Map<String, String> map = new HashMap<>();
        map.put("text" , word);
        RequestBody requestBody = HttpManager.getInstance().createRequestBody(map);
        Call<Element> searchBarCall = apiService.searchBar(requestBody);
        searchBarCall.enqueue(new Callback<Element>() {
            @Override
            public void onResponse(Call<Element> call, Response<Element> response) {
                if(response.isSuccessful()){
                    List<Bar> barList = response.body().getBarList();
                    SearchBarAdapter adapter = new SearchBarAdapter(barList);
                    rv.setAdapter(adapter);
                }else{
                    // TODO: 4/16/16 AD Handle error
                    Log.e("error" , response.raw().toString());
                }
            }

            @Override
            public void onFailure(Call<Element> call, Throwable t) {
                // TODO: 4/16/16 AD Handle error
                Log.e("failed" , t.getMessage());
            }
        });
    }
}
