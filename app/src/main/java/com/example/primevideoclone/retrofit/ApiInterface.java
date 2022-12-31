package com.example.primevideoclone.retrofit;

import com.example.primevideoclone.model.AllCategory;
import com.example.primevideoclone.model.BannerMovies;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("prime.json")
    Observable<List<BannerMovies>> getAllBanners();

    @GET("{categoryid}/categoria.json")
    Observable<List<AllCategory>>getAllCategoryMovie(@Path("categoryid") int categoryid);



}
