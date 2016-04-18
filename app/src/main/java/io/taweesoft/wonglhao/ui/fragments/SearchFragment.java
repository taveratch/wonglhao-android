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
import io.taweesoft.wonglhao.managers.DataStorage;
import io.taweesoft.wonglhao.managers.HttpManager;
import io.taweesoft.wonglhao.managers.Load;
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
public class SearchFragment extends Fragment implements MyObservable,Observer{

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
        Map<String, String> map = new HashMap<>();
        map.put("text" , word);
        Load load = Load.newInstance();
        load.addObserver(this);
        load.searchBar(map);
    }

    @Override
    public void update(java.util.Observable observable, Object o) {
        if(o == null) return;
        Response<Element> response = (Response<Element>)o;
        if(response.isSuccessful()){
            List<Bar> barList = response.body().getBarList();
            for(Bar bar : barList)
                DataStorage.barMap.put(bar.getId() , bar);
            SearchBarAdapter adapter = new SearchBarAdapter(barList);
            rv.setAdapter(adapter);
        }else{
            // TODO: 4/16/16 AD Handle error
            Log.e("error" , response.raw().toString());
        }
    }
}
