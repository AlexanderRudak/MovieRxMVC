package bonosoft.rudak.movie.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Movie {

    @SerializedName("id")
    String id;
    @SerializedName("original_title")
    String originalTitle;
    @SerializedName("poster_path")
    String posterPath;
    @SerializedName("vote_count")
    String voteCount;
    @SerializedName("vote_average")
    String voteAverage;
    @SerializedName("release_date")
    String releaseDate;
    @SerializedName("overview")
    String overview;
    @SerializedName("runtime")
    String runtime;

    List<String> trailers;
    List<String> reviews;

    public Movie(){
        this.trailers = new ArrayList<String>();
        this.reviews = new ArrayList<String>();
    }

    public Movie(String id,
                 String originalTitle,
                 String posterPath,
                 String voteCount,
                 String voteAverage,
                 String releaseDate,
                 String overview ,
                 String runtime ){

        this.id = id;
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.runtime = runtime;

        this.trailers = new ArrayList<String>();
        this.reviews = new ArrayList<String>();
    }

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getOriginalTitle(){
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle){
        this.originalTitle = originalTitle;
    }

    public String getPosterPath(){
        return posterPath;
    }

    public void setPosterPath(String posterPath){
        this.posterPath = posterPath;
    }

    public String getVoteCount(){
        return voteCount;
    }

    public void setVoteCount(String voteCount){
        this.voteCount = voteCount;
    }

    public String getVoteAverage(){
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage){
        this.voteAverage = voteAverage;
    }

    public String getReleaseDate(){
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }

    public String getOverview(){
        return overview;
    }

    public void setOverview(String overview){
        this.overview = overview;
    }

    public  String getRuntime(){
        return  runtime;
    }

    public void setRuntime(String runtime){
        this.runtime = runtime;
    }

    public List<String> getTrailers(){
        return trailers;
    }

    public void setTrailers(List<String> trailers){
        this.trailers = trailers;
    }

    public List<String> getReviews(){
        return reviews;
    }

    public void setReviews(List<String> reviews){
        this.reviews = reviews;
    }
}
