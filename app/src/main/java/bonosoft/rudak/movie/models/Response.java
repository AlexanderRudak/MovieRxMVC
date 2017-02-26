package bonosoft.rudak.movie.models;


import java.util.ArrayList;
import java.util.List;

import bonosoft.rudak.movie.util.Constants;

public class Response {

    int page;
    int total_pages;
    List<Movie> movies;
    String sort;

    public Response(){
        this.page = 0;
        this.total_pages = 0;
        this.movies = new ArrayList<Movie>();
        this.sort = Constants.SORT_POPULAR;;
    }

    public Response(int page, int total_pages, List<Movie> movies, String sort){
        this.page = page;
        this.total_pages = total_pages;
        this.movies = new ArrayList<Movie>();
        this.movies.addAll(movies);
        this.sort = sort;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page){
        this.page = page;
    }

    public int getTotal_pages() {
        return  total_pages;
    }

    public void setTotal_pages(int total_pages){
        this.total_pages = total_pages;
    }

    public List<Movie> getMovies(){
        return movies;
    }

    public void setMovies(List<Movie> movies){
        this.movies.clear();
        this.movies.addAll(movies);
    }

    public String getSort(){
        return sort;
    }

    public void setSort(String sort){
        this.sort = sort;
    }
}
