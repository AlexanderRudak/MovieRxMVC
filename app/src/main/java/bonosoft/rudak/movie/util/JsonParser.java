package bonosoft.rudak.movie.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import bonosoft.rudak.movie.models.Movie;
import bonosoft.rudak.movie.models.Response;
import bonosoft.rudak.movie.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {

    public static List<String> JsonToListReviews(String strJson) {
        List<String> reviews = new ArrayList<String>();
        if (strJson.equalsIgnoreCase("")) {
            return reviews;
        }

        try {
            JSONObject dataJsonObj = null;
            String strJsonUTF = new String(strJson.getBytes("UTF-8"),"UTF-8") ;
            dataJsonObj = new JSONObject(strJsonUTF);
            JSONArray moviesArr = dataJsonObj.getJSONArray("results");

            for (int i = 0; i < moviesArr.length(); i++) {
                JSONObject Obj = moviesArr.getJSONObject(i);
                reviews.add(Obj.getString("content"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return reviews;
    }

    public static List<String> JsonToListVideos(String strJson) {
        List<String> urlVideos = new ArrayList<String>();
        if (strJson.equalsIgnoreCase("")) {
            return urlVideos;
        }

        try {
            JSONObject dataJsonObj = null;
            String strJsonUTF = new String(strJson.getBytes("UTF-8"),"UTF-8") ;
            dataJsonObj = new JSONObject(strJsonUTF);
            JSONArray moviesArr = dataJsonObj.getJSONArray("results");

            for (int i = 0; i < moviesArr.length(); i++) {
                JSONObject Obj = moviesArr.getJSONObject(i);
                urlVideos.add(Constants.YOUTUBE_URL + Obj.getString("key"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return urlVideos;
    }

    public static Movie JsonToDetail(String strJson) {
        Movie movie = new Movie();
        if (strJson.equalsIgnoreCase("")) {
            return movie;
        }

        try {
            JSONObject dataJsonObj = null;
            String strJsonUTF = new String(strJson.getBytes("UTF-8"),"UTF-8") ;
            dataJsonObj = new JSONObject(strJsonUTF);

                movie = new Movie(
                        dataJsonObj.getString("id"),
                        dataJsonObj.getString("original_title"),
                        Constants.POSTER_PARENT_PATH+dataJsonObj.getString("poster_path"),
                        dataJsonObj.getString("vote_count"),
                        dataJsonObj.getString("vote_average"),
                        dataJsonObj.getString("release_date"),
                        dataJsonObj.getString("overview"),
                        dataJsonObj.getString("runtime")
                );

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return movie;
    }

    public static Response JsonToListMovie(String strJson){
        List<Movie> movies = new ArrayList<Movie>();
        Response response = new Response();
        if(strJson.equalsIgnoreCase("")){
            return response;
        }
        try {
            JSONObject dataJsonObj = null;
            String strJsonUTF = new String(strJson.getBytes("UTF-8"),"UTF-8") ;
            dataJsonObj = new JSONObject(strJsonUTF);

            response.setPage(dataJsonObj.getInt("page"));
            response.setTotal_pages(dataJsonObj.getInt("total_pages"));
            JSONArray moviesArr = dataJsonObj.getJSONArray("results");

            for (int i = 0; i < moviesArr.length(); i++) {
                JSONObject Obj = moviesArr.getJSONObject(i);
                movies.add(new Movie(
                        Obj.getString("id"),
                        Obj.getString("original_title"),
                        Constants.POSTER_PARENT_PATH+Obj.getString("poster_path"),
                        Obj.getString("vote_count"),
                        Obj.getString("vote_average"),
                        Obj.getString("release_date"),
                        Obj.getString("overview"),
                        ""
                ));
            }

            response.setMovies(movies);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return response;
    }

}
