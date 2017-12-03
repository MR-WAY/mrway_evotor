package io.repoint.mrway;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MrWayService {

    @GET("Evotor/Order")
    Call<List<Order>> getOrders();

    @POST("Evotor/Order/Pack")
    Call<TakeAway> packOrder(@Body TakeAway takeAway);

}
