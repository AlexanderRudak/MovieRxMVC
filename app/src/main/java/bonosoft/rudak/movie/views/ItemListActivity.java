package bonosoft.rudak.movie.views;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import bonosoft.rudak.movie.presenters.IPresenter;
import bonosoft.rudak.movie.presenters.ItemListPresenter;
import bonosoft.rudak.movie.util.Constants;
import bonosoft.rudak.movie.R;
import bonosoft.rudak.movie.adapters.RecyclerMovieAdapter;
import bonosoft.rudak.movie.models.Movie;
import bonosoft.rudak.movie.models.Response;
import java.util.ArrayList;
import java.util.List;


public class ItemListActivity extends MvpAppCompatActivity
        implements RecyclerMovieAdapter.OnItemClickListener,
        ItemListView {

    private boolean twoPane;
    private List<Movie> movies = new ArrayList<Movie>();
    private RecyclerView recyclerView;
    private RecyclerMovieAdapter adapter;
    private GridLayoutManager recyclerViewLayoutManager;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private OnLoadMoreListener onLoadMoreListener;

    int page = 0;
    int total_pages;
    boolean loading;
    String sort;

    ItemListPresenter itemListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        findView();
        setupAdapter();
        addListener();

        itemListPresenter = (ItemListPresenter)presenter;
        sort = itemListPresenter.getSort();
        itemListPresenter.loadMovies(sort, page+1);
    }

    @Override
    protected void setViewId(){
        viewId = 1;
    }

    @Override
    protected IPresenter NewPresenter(){
        return new ItemListPresenter();
    }

    void findView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.item_list);
        progressBar = (ProgressBar)  findViewById(R.id.progressBar);

        if (findViewById(R.id.item_detail_container) != null) {
            twoPane = true;
        }
    }

    void setupAdapter(){
        int col = twoPane ? 3 : 2;

        recyclerViewLayoutManager = new GridLayoutManager(this, col);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        adapter = new RecyclerMovieAdapter(this, movies);
        adapter.SetOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    void addListener(){
        onLoadMoreListener = new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if(page<total_pages)
                    itemListPresenter.loadMovies(sort, page+1);
            }
        };

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleThreshold = 4;
            int totalItemCount = recyclerViewLayoutManager.getItemCount();
            int lastVisibleItem = recyclerViewLayoutManager.findLastVisibleItemPosition();

            if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                loading = true;
                if (onLoadMoreListener != null) {
                    onLoadMoreListener.onLoadMore();
                }
            }
            }
        });
    }

    public void setLoaded() {
        loading = false;
    }

    interface OnLoadMoreListener {
        void onLoadMore();
    }


    @Override
    public void onItemClick(View view, int position) {
        if (twoPane) {
            ;
        } else {
            ;
        }
    }

    @Override
    public void onMoviesStartLoading(){
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMoviesLoaded(Response data) {
        progressBar.setVisibility(View.GONE);
        setLoaded();

        if(!sort.equalsIgnoreCase(data.getSort()))
            movies.clear();

        if(page < data.getPage())
            movies.addAll(data.getMovies());

        sort = data.getSort();
        page = data.getPage();
        total_pages = data.getTotal_pages();

        if(adapter != null)
            adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_film, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.sort_popular:
                sortPopular();
                break;
            case R.id.sort_rating:
                sortRating();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sortPopular(){
        toolbar.setTitle(R.string.title_sort_popular);
        page=0;
        itemListPresenter.loadMovies(Constants.SORT_POPULAR, page+1);
    }

    public void sortRating(){
        toolbar.setTitle(R.string.title_sort_rating);
        page=0;
        itemListPresenter.loadMovies(Constants.SORT_RATING, page+1);
    }


}
