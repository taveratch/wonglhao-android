package io.taweesoft.wonglhao.ui.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import net.simonvt.menudrawer.MenuDrawer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.taweesoft.wonglhao.R;
import io.taweesoft.wonglhao.managers.Constant;
import io.taweesoft.wonglhao.managers.DataStorage;
import io.taweesoft.wonglhao.managers.Utility;
import io.taweesoft.wonglhao.models.SideBarItem;
import io.taweesoft.wonglhao.ui.adapters.SideBarAdapter;
import io.taweesoft.wonglhao.ui.fragments.NearByFragment;
import io.taweesoft.wonglhao.ui.fragments.PromotionFragment;
import io.taweesoft.wonglhao.ui.fragments.BarsFragment;
import io.taweesoft.wonglhao.ui.fragments.SearchFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements Observer{

    private MenuDrawer menuDrawer;
    private List<SideBarItem> sideBarItemList;
    private int currentActivatedItem = 0;
    private PromotionFragment promotionFragment;
    private BarsFragment pubsFragment;
    private SearchFragment searchFragment;
    private NearByFragment nearByFragment;

    @Bind(R.id.lv) ListView sideBar;
    @Bind(R.id.tvName) TextView tvName;
    @Bind(R.id.tvEmail) TextView tvEmail;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utility.canAccessLocation(this); //check access loaction permission.
        menuDrawer = MenuDrawer.attach(this);
        menuDrawer.setContentView(R.layout.activity_main);
        menuDrawer.setMenuView(R.layout.left_drawer_layout);
        ButterKnife.bind(menuDrawer);
        ButterKnife.bind(this);
        initComponents();
    }

    public void initComponents() {
        promotionFragment = new PromotionFragment();
        pubsFragment = new BarsFragment();
        searchFragment = new SearchFragment();
        nearByFragment = new NearByFragment();
        promotionFragment.addObserver(this);
        pubsFragment.addObserver(this);
        searchFragment.addObserver(this);
        nearByFragment.addObserver(this);

        tvName.setText(DataStorage.user.getUsername());
        tvEmail.setText(DataStorage.user.getEmail());
        sideBarItemList = new ArrayList<>();
        sideBarItemList.add(new SideBarItem(R.drawable.promotion ,getString(R.string.promotion)));
        sideBarItemList.add(new SideBarItem(R.drawable.wine, getString(R.string.pubBar)));
        sideBarItemList.add(new SideBarItem(R.drawable.search , getString(R.string.search)));
        sideBarItemList.add(new SideBarItem(R.drawable.map , getString(R.string.nearBy)));
        sideBarItemList.get(0).setActive(true);

        final SideBarAdapter adapter = new SideBarAdapter(this,R.layout.left_menu_item_layout,sideBarItemList);
        sideBar.setAdapter(adapter);
        sideBar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SideBarItem current = sideBarItemList.get(currentActivatedItem);
                SideBarItem newItem = sideBarItemList.get(position);
                current.setActive(false);
                newItem.setActive(true);
                adapter.notifyDataSetChanged();
                menuDrawer.toggleMenu(true);
                switchFragment(position);
                currentActivatedItem = position;
            }
        });
        switchFragment(0);

    }

    public void replaceFragment(Fragment fragmnet) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragmnet)
                .addToBackStack(null)
                .commit();
    }

    public void switchFragment(int position) {
        switch (position){
            case 0 :
                replaceFragment(promotionFragment);
                break;
            case 1 :
                replaceFragment(pubsFragment);
                break;
            case 2 :
                replaceFragment(searchFragment);
                break;
            case 3 :
                replaceFragment(nearByFragment);
                break;
        }
    }

    @Override
    public void update(Observable observable, Object data) {
        menuDrawer.toggleMenu(true);
    }

    @OnClick(R.id.containerSignout)
    public void signout() {
        SharedPreferences.Editor editor = getSharedPreferences(Constant.APP_NAME , MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
        finish();
    }
}
