package com.jude.automobile.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.internal.MDButton;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.jude.automobile.R;
import com.jude.automobile.data.DataModel;
import com.jude.automobile.data.SearchHistoryModel;
import com.jude.automobile.domain.entities.Line;
import com.jude.automobile.domain.entities.Model;
import com.jude.automobile.domain.entities.Type;
import com.jude.automobile.presenter.MainPresenter;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.swipbackhelper.SwipeBackHelper;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observable;
import rx.functions.Action1;

@RequiresPresenter(MainPresenter.class)
public class MainActivity extends BeamBaseActivity<MainPresenter>
        implements NavigationView.OnNavigationItemSelectedListener {


    @InjectView(R.id.recycler)
    EasyRecyclerView recycler;
    @InjectView(R.id.fab_menu)
    FloatingActionMenu fabMenu;
    @InjectView(R.id.nav_view)
    NavigationView navView;
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.line)
    FloatingActionButton line;
    @InjectView(R.id.type)
    FloatingActionButton type;
    @InjectView(R.id.model)
    FloatingActionButton model;

    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBackHelper.getCurrentPage(this).setSwipeBackEnable(false);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        recycler.setEmptyView(R.layout.view_empty_main);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter = new MainAdapter(this));

        line.setOnClickListener(v->{
            fabMenu.close(true);
            createLineSearchDialog();
        });
        type.setOnClickListener(v->{
            fabMenu.close(true);
            createTypeSearchDialog();
        });
        model.setOnClickListener(v->{
            fabMenu.close(true);
            createModelSearchDialog();
        });

        initHistory();
    }

    private RecyclerArrayAdapter.ItemView mSearchHeader;
    private void initHistory(){
        mSearchHeader = new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View header = LayoutInflater.from(MainActivity.this).inflate(R.layout.head_search,parent,false);
                header.findViewById(R.id.clear).setOnClickListener(v->{
                    SearchHistoryModel.getInstance().clear();
                    adapter.clear();
                });
                return header;
            }

            @Override
            public void onBindView(View headerView) {

            }
        };
        adapter.addHeader(mSearchHeader);
        adapter.addAll(SearchHistoryModel.getInstance().getSearchHistory());
    }

    public void addData(ArrayList arrayList){
        adapter.clear();
        if (mSearchHeader!=null){
            adapter.removeHeader(mSearchHeader);
            mSearchHeader=null;
        }
        adapter.addAll(arrayList);
    }

    private void createLineSearchDialog(){
        new MaterialDialog.Builder(this)
                .title("搜索车系")
                .input("车系名字", "", (dialog, input) -> {
                    getExpansion().showProgressDialog("搜索中");
                    DataModel.getInstance().searchLine(input.toString())
                            .finallyDo(() -> getExpansion().dismissProgressDialog())
                            .subscribe(new Action1<ArrayList<Line>>() {
                        @Override
                        public void call(ArrayList<Line> lines) {
                            addData(lines);
                        }
                    });
                })
                .positiveText("搜索")
                .negativeText("取消")
                .show();
    }

    private void createTypeSearchDialog(){
        new MaterialDialog.Builder(this)
                .title("搜索车型")
                .input("车型名字", "", (dialog, input) -> {
                    getExpansion().showProgressDialog("搜索中");
                    DataModel.getInstance().searchType(input.toString())
                            .finallyDo(() -> getExpansion().dismissProgressDialog())
                            .subscribe(new Action1<ArrayList<Type>>() {
                        @Override
                        public void call(ArrayList<Type> types) {
                            addData(types);
                        }
                    });
                })
                .positiveText("搜索")
                .negativeText("取消")
                .show();
    }

    private void createModelSearchDialog(){
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("搜索车款")
                .customView(R.layout.dialog_model_search,false)
                .negativeText("取消")
                .positiveText("搜索")
                .show();
        View view = dialog.getCustomView();
        MDButton positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
        EditText input = (EditText) view.findViewById(R.id.input);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.search_type);
        positiveAction.setOnClickListener(v->{
            getExpansion().showProgressDialog("搜索中");
            Observable<ArrayList<Model>> observable = null;
            switch (radioGroup.getCheckedRadioButtonId()){
                case R.id.search_name:
                    observable = DataModel.getInstance().searchModelByName(input.getText().toString());
                    break;
                case R.id.search_engine:
                    observable = DataModel.getInstance().searchModelByEngine(input.getText().toString());
                    break;
            }
            if (observable!=null) observable
                    .finallyDo(() -> getExpansion().dismissProgressDialog())
                    .subscribe(this::addData);
            dialog.dismiss();
        });
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
