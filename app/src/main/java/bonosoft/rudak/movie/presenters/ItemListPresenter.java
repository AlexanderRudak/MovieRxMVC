package bonosoft.rudak.movie.presenters;


import bonosoft.rudak.movie.models.Response;
import bonosoft.rudak.movie.retrofit.MoviesService;
import bonosoft.rudak.movie.util.Constants;
import bonosoft.rudak.movie.util.JsonParser;
import bonosoft.rudak.movie.views.ItemListView;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
            import rx.Subscription;
            import rx.android.schedulers.AndroidSchedulers;
            import rx.schedulers.Schedulers;

            public class ItemListPresenter extends BasePresenter{
                Response response = new Response();
                Subscription subscription;
                Observable<String> observableRetrofit;

                public String getSort(){
                    return  response.getSort();
                }

                public void loadMovies(String sort, int page) {
                    if(sort.equalsIgnoreCase(response.getSort()) && page <= response.getPage()){
                        if(view instanceof ItemListView)
                            ((ItemListView)view).onMoviesLoaded(response);
                        return;
                    }

                    Retrofit retrofit = new Retrofit.Builder()
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .baseUrl("https://api.themoviedb.org/3/movie/").build();
                    MoviesService service = retrofit.create(MoviesService.class);

                    ((ItemListView)view).onMoviesStartLoading();

                    try {
                        switch (sort) {
                            case Constants.SORT_POPULAR:
                                observableRetrofit = service.getMoviePopular(Constants.API_KEY, String.valueOf(page));
                                break;
                            case Constants.SORT_RATING:
                                observableRetrofit = service.getMovieTopRated(Constants.API_KEY, String.valueOf(page));
                                break;
                            default:
                                observableRetrofit = service.getMoviePopular(Constants.API_KEY, String.valueOf(page));
                                break;
                        }

                        subscription = observableRetrofit
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .map(s -> {
                                    return JsonParser.JsonToListMovie(s);
                                })
                    .subscribe( data -> {
                        if(page==1)
                            response.getMovies().clear();

                        data.setSort(sort);
                        response.setSort(sort);
                        response.setPage(data.getPage());
                        response.setTotal_pages(data.getTotal_pages());
                        response.getMovies().addAll(data.getMovies());

                        ((ItemListView)view).onMoviesLoaded(data);
                    }
                    );

        } catch ( Exception e ){
        }
    }


}
