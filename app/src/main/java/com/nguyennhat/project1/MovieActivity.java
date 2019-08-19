package com.nguyennhat.project1;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends AppCompatActivity implements ClickListener, SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    private List<NewMovie> list = new ArrayList<>();
    private NewMoviesAdapter adapter;
    private RecyclerView recycleView;
    private boolean isGridMode = false;
    private SearchView mSearchView;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recycleView = findViewById(R.id.movierecycle);
        recycleView.setHasFixedSize(true);
        prepareMovie();
        adapter = new NewMoviesAdapter(list, this);
        adapter.setListener(this);
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        recycleView.setLayoutManager(layoutManager);
        recycleView.setAdapter(adapter);



    }

    private RecyclerView.LayoutManager getLayoutManager() {
        RecyclerView.LayoutManager layoutManager;
        if (isGridMode) {
            layoutManager = new GridLayoutManager(this, 2);
        } else {
            layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        }
        return  layoutManager;
    }

    private void prepareMovie() {
        list.add(new NewMovie("Avengers", "22-05-2019", "6.4/10", "good", R.mipmap.anhmovie));
        list.add(new NewMovie("X-Men", "22-05-2019", "6.4/10", "good", R.mipmap.anhxmen));
        list.add(new NewMovie("Aquaman", "22-05-2019", "6.4/10", "good", R.mipmap.anhmovie_4));
        list.add(new NewMovie("Oggy", "22-05-2019", "6.4/10", "good", R.mipmap.anhmovie_5));
        list.add(new NewMovie("Tom And Jerry", "22-05-2019", "6.4/10", "good", R.mipmap.anhmovie_3));
        list.add(new NewMovie("ABC", "22-05-2019", "6.4/10", "good", R.mipmap.anhmovie));
        list.add(new NewMovie("Pink Panther", "22-05-2019", "6.4/10", "good", R.mipmap.anhmovie));
        list.add(new NewMovie("Masupilami", "22-05-2019", "6.4/10", "good", R.mipmap.anhmovie));
        list.add(new NewMovie("Doraemon", "22-05-2019", "6.4/10", "good", R.mipmap.anhmovie_2));
        list.add(new NewMovie("Pokemon", "22-05-2019", "6.4/10", "good", R.mipmap.anhpokemon));


        // Todo implement later
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        setupSearchView();
        return super.onCreateOptionsMenu(menu);
    }

    private void setupSearchView() {

        mSearchView.setIconifiedByDefault(true);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            List<SearchableInfo> searchables = searchManager.getSearchablesInGlobalSearch();

            // Try to use the "applications" global search provider
            SearchableInfo info = searchManager.getSearchableInfo(getComponentName());
            for (SearchableInfo inf : searchables) {
                if (inf.getSuggestAuthority() != null
                        && inf.getSuggestAuthority().startsWith("applications")) {
                    info = inf;
                }
            }
            mSearchView.setSearchableInfo(info);
        }

        mSearchView.setOnQueryTextListener(this);
        mSearchView.setOnCloseListener(this);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d("TAGGG", newText);
        adapter.getFilter().filter(newText);
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.switch_mode:
                isGridMode = !isGridMode;
                RecyclerView.LayoutManager layoutManager = getLayoutManager();
                recycleView.setLayoutManager(layoutManager);
                adapter.setMode(isGridMode);
                if (isGridMode) {
                    item.setIcon(R.drawable.ic_switch);
                } else {
                    item.setIcon(R.drawable.ic_gridview);
                }
                Toast.makeText(MovieActivity.this, "da hien thi", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(MovieActivity.this,DetailActivity.class);
        intent.putExtra("key",list.get(position).getMovieName());
        intent.putExtra("rate", list.get(position).getRating());
        intent.putExtra("img", list.get(position).getImgView());
        startActivity(intent);
    }

    @Override
    public void onLongClick(int position) {

    }

    @Override
    public boolean onClose() {
        return false;
    }
}

