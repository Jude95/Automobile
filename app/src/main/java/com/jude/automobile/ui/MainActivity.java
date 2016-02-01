package com.jude.automobile.ui;

import android.content.Intent;
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
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.jude.automobile.R;
import com.jude.automobile.data.AccountModel;
import com.jude.automobile.data.DataModel;
import com.jude.automobile.data.SearchHistoryModel;
import com.jude.automobile.domain.entities.Search;
import com.jude.automobile.presenter.MainPresenter;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.swipbackhelper.SwipeBackHelper;
import com.jude.utils.JUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(MainPresenter.class)
public class MainActivity extends BeamBaseActivity<MainPresenter>
        implements NavigationView.OnNavigationItemSelectedListener {

    private boolean isHistoryModel = false;

    @Bind(R.id.recycler)
    EasyRecyclerView recycler;
    @Bind(R.id.fab_menu)
    FloatingActionMenu fabMenu;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.line)
    FloatingActionButton line;
    @Bind(R.id.type)
    FloatingActionButton type;
    @Bind(R.id.model)
    FloatingActionButton model;
    @Bind(R.id.all)
    FloatingActionButton all;
    TextView name;
    TextView number;

    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBackHelper.getCurrentPage(this).setSwipeBackEnable(false);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (!AccountModel.getInstance().isActivity()){
            startActivity(new Intent(this, TimeActivity.class));
        }

        recycler.setEmptyView(R.layout.view_empty_main);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);
        name = (TextView) navView.getHeaderView(0).findViewById(R.id.name);
        number = (TextView) navView.getHeaderView(0).findViewById(R.id.number);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter = new MainAdapter(this));

        line.setOnClickListener(v -> {
            fabMenu.close(true);
            createLineSearchDialog();
        });
        type.setOnClickListener(v -> {
            fabMenu.close(true);
            createTypeSearchDialog();
        });
        model.setOnClickListener(v -> {
            fabMenu.close(true);
            createModelSearchDialog();
        });
        all.setOnClickListener(v->{
            fabMenu.close(true);
            startActivity(new Intent(this,LineAllActivity.class));
        });
        initHistory();
        AccountModel.getInstance().getAccountSubject().subscribe(account -> {
            if (account!=null){
                name.setText(account.getName());
                number.setText(account.getAccount());
            }else {
                name.setText("");
                number.setText("");
            }
        });
        AccountModel.getInstance().refreshAccount();
    }

    private RecyclerArrayAdapter.ItemView mSearchHeader;

    private void initHistory() {
        isHistoryModel = true;
        mSearchHeader = new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View header = LayoutInflater.from(MainActivity.this).inflate(R.layout.head_search, parent, false);
                header.findViewById(R.id.clear).setOnClickListener(v -> {
                    SearchHistoryModel.getInstance().clear();
                    adapter.clear();
                });
                return header;
            }

            @Override
            public void onBindView(View headerView) {

            }
        };
        adapter.clear();
        adapter.addHeader(mSearchHeader);
        adapter.addAll(SearchHistoryModel.getInstance().getSearchHistory());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (!isHistoryModel) initHistory();
            else super.onBackPressed();
        }
    }

    public void addData(List arrayList) {
        isHistoryModel = false;
        if (arrayList == null || arrayList.size()==0){
            JUtils.Toast("没有找到数据哟");
        }
        adapter.clear();
        if (mSearchHeader != null) {
            adapter.removeHeader(mSearchHeader);
            mSearchHeader = null;
        }
        adapter.addAll(arrayList);
    }

    public void startSearch(Search search) {
        getExpansion().showProgressDialog("搜索中");
        DataModel.getInstance().dispatchSearch(search)
                .finallyDo(() -> getExpansion().dismissProgressDialog())
                .subscribe(this::addData);
    }

    private void createLineSearchDialog() {
        new MaterialDialog.Builder(this)
                .title("搜索车系")
                .input("车系名字", "", (dialog, input) -> {
                    getExpansion().showProgressDialog("搜索中");
                    DataModel.getInstance().searchLine(input.toString())
                            .finallyDo(() -> getExpansion().dismissProgressDialog())
                            .subscribe(this::addData);
                })
                .positiveText("搜索")
                .negativeText("取消")
                .show();
    }

    private void createTypeSearchDialog() {
        new MaterialDialog.Builder(this)
                .title("搜索车型")
                .input("车型名字", "", (dialog, input) -> {
                    getExpansion().showProgressDialog("搜索中");
                    DataModel.getInstance().searchType(input.toString())
                            .finallyDo(() -> getExpansion().dismissProgressDialog())
                            .subscribe(this::addData);
                })
                .positiveText("搜索")
                .negativeText("取消")
                .show();
    }

    private void createModelSearchDialog() {
        new MaterialDialog.Builder(this)
                .title("搜索车款")
                .input("车款名字／发动机型号", "", (dialog, input) -> {
                    getExpansion().showProgressDialog("搜索中");
                    DataModel.getInstance().searchModel(input.toString())
                            .finallyDo(() -> getExpansion().dismissProgressDialog())
                            .subscribe(this::addData);
                })
                .positiveText("搜索")
                .negativeText("取消")
                .show();
    }

//    private void createCustomModelSearchDialog() {
//        MaterialDialog dialog = new MaterialDialog.Builder(this)
//                .title("搜索车款")
//                .customView(R.layout.dialog_model_search, false)
//                .negativeText("取消")
//                .positiveText("搜索")
//                .show();
//        View view = dialog.getCustomView();
//        MDButton positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
//        EditText input = (EditText) view.findViewById(R.id.input);
//        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.search_type);
//        positiveAction.setOnClickListener(v -> {
//            getExpansion().showProgressDialog("搜索中");
//            Observable<ArrayList<Model>> observable = null;
//            switch (radioGroup.getCheckedRadioButtonId()) {
//                case R.id.search_name:
//                    observable = DataModel.getInstance().searchModelByName(input.getText().toString());
//                    break;
//                case R.id.search_engine:
//                    observable = DataModel.getInstance().searchModelByEngine(input.getText().toString());
//                    break;
//            }
//            if (observable != null) observable
//                    .finallyDo(() -> getExpansion().dismissProgressDialog())
//                    .subscribe(this::addData);
//            dialog.dismiss();
//        });
//    }



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

        if (id == R.id.information) {
            startActivity(new Intent(this, TimeActivity.class));
        } else if (id == R.id.manager) {
            if (AccountModel.getInstance().isManager())
                startActivity(new Intent(this, ManagerActivity.class));
            else
                JUtils.Toast("您不是管理猿哟");
        } else if (id == R.id.about){
            startActivity(new Intent(this, AboutActivity.class));
        } else if (id == R.id.logout){
            AccountModel.getInstance().logout();
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
