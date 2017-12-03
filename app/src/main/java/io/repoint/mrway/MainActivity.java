package io.repoint.mrway;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView order_rv;
    SwipeRefreshLayout main_srf;

    OrderAdapter orderAdapter;
    public static MrWayService mrWayService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        order_rv = findViewById(R.id.order_rv);
        main_srf = findViewById(R.id.main_srf);
        main_srf.setOnRefreshListener(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://178.62.241.56/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mrWayService = retrofit.create(MrWayService.class);

        main_srf.setRefreshing(true);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        mrWayService.getOrders().enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                List<Order> orders = response.body();
                orderAdapter = new OrderAdapter(MainActivity.this, orders);
                order_rv.setAdapter(orderAdapter);
                main_srf.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
            }
        });
    }
}
