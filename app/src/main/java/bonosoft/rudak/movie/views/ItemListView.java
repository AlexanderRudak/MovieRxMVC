package bonosoft.rudak.movie.views;


import java.util.List;

import bonosoft.rudak.movie.models.Movie;
import bonosoft.rudak.movie.models.Response;

public interface ItemListView {
    void onMoviesLoaded(Response response);
    void onMoviesStartLoading();
}
