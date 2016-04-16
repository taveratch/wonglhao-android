package io.taweesoft.wonglhao.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Observer;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.taweesoft.wonglhao.R;
import io.taweesoft.wonglhao.managers.MyObservable;
import io.taweesoft.wonglhao.models.Observable;

/**
 * Created by TAWEESOFT on 4/14/16 AD.
 */
public class SearchFragment extends Fragment implements MyObservable{

    private Observable observable = new Observable();

    @Bind(R.id.toolbar) Toolbar toolbar;

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
    }

    @Override
    public void addObserver(Observer observer) {
        observable.addObserver(observer);
    }
}
