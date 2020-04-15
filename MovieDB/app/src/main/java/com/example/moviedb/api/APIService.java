package com.example.moviedb.api;

import com.example.moviedb.models.GetMoviesResponse;



import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    String apiKey = "01368507819da249cebd60071de61140";
    @GET("discover/movie?api_key=" + apiKey)
     Call<GetMoviesResponse> getMovies();

}
