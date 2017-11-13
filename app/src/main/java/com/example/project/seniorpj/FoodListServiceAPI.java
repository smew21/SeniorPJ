package com.example.project.seniorpj;

import com.example.project.seniorpj.Food.DAO_FoodList.DAO_FoodList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by SMEW on 24/6/2560.
 */

public interface FoodListServiceAPI {

    String base_url = "http://kcaltable.firebaseio.com";

    @GET("/{json}")
    Call<DAO_FoodList> getfoodlist(@Path("json") String json);

}
