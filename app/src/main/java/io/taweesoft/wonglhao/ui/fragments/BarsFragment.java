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

import java.util.List;
import java.util.Observer;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.taweesoft.wonglhao.R;
import io.taweesoft.wonglhao.managers.APIService;
import io.taweesoft.wonglhao.managers.DataStorage;
import io.taweesoft.wonglhao.managers.HttpManager;
import io.taweesoft.wonglhao.managers.MyObservable;
import io.taweesoft.wonglhao.models.Bar;
import io.taweesoft.wonglhao.models.Element;
import io.taweesoft.wonglhao.models.Observable;
import io.taweesoft.wonglhao.ui.adapters.BarAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TAWEESOFT on 4/14/16 AD.
 */
public class BarsFragment extends Fragment implements MyObservable {

    private Observable observable = new Observable();

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.rv) RecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pub_fragment_layout,container, false);
        ButterKnife.bind(this,v);
        initComponents();
        APIService apiService = HttpManager.getInstance().getAPIService(APIService.class);
        Call<Element> getAllBarCall = apiService.getAllBar(-1);
        getAllBarCall.enqueue(new Callback<Element>() {
            @Override
            public void onResponse(Call<Element> call, Response<Element> response) {
                if(response.isSuccessful()){
                    List<Bar> barList = response.body().getBarList();

                    //Save all bars to barMap for using in another activity.
                    DataStorage.barMap.clear();
                    for(Bar bar : barList)
                        DataStorage.barMap.put(bar.getId() , bar);

                    RecyclerView.LayoutManager llm = new LinearLayoutManager(BarsFragment.this.getContext());
                    rv.setLayoutManager(llm);
                    BarAdapter adapter = new BarAdapter(barList);
                    rv.setAdapter(adapter);
                }else{
                    // TODO: 4/15/16 AD Handle error
                    Log.e("failed" , response.raw().toString());
                }
            }

            @Override
            public void onFailure(Call<Element> call, Throwable t) {
                Log.e("error" , t.getMessage());
            }
        });
        return v;
    }

    public void initComponents() {
        toolbar.setTitle(getString(R.string.pubBar));
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                observable.setChanged();
                observable.notifyObservers();
            }
        });
    }

    @Override
    public void addObserver(Observer observer) {
        observable.addObserver(observer);
    }
}
