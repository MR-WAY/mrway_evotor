package io.repoint.mrway;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mishu on 03.12.2017.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {

    private final MainActivity context;
    private final List<Order> orders;

    public OrderAdapter(MainActivity context, List<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final OrderViewHolder holder, int position) {
        final Order order = orders.get(position);

        holder.number_tv.setText(order.id.split("-")[0]);
        holder.cost_tv.setText(String.valueOf(order.cost));
        holder.lines_rv.setAdapter(new OrderLineAdapter(context, order.lines));

        if (order.orderStatus.equals("packed")) {
            holder.indicator_v.setBackgroundColor(context.getResources().getColor(R.color.mr_green));
        } else {
            holder.indicator_v.setBackgroundColor(context.getResources().getColor(R.color.mr_red));
        }

        if (order.deliveryStatus.equals("accepted") && order.orderStatus.equals("packed")) {
            holder.time_tv.setText("Код для выдачи: " + String.valueOf(order.code));
            holder.pack_b.setVisibility(View.GONE);
        } else {
            holder.pack_b.setVisibility(View.VISIBLE);
            holder.pack_b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TakeAway takeAway = new TakeAway();
                    takeAway.id = order.id;
                    takeAway.code = getRandomArbitrary(1000, 9999);

                    MainActivity.mrWayService.packOrder(takeAway).enqueue(new Callback<TakeAway>() {
                        @Override
                        public void onResponse(Call<TakeAway> call, Response<TakeAway> response) {
                            OrderIt.orderIt(order, context);
                            holder.pack_b.setVisibility(View.GONE);
                        }

                        @Override
                        public void onFailure(Call<TakeAway> call, Throwable t) {
                        }
                    });
                }
            });

            if (order.deliveryStatus.equals("untreated")) {
                holder.time_tv.setText("Поиск курьера...");
            } else if (order.deliveryStatus.equals("accepted")) {
                holder.time_tv.setText("Заказ принят курьером!");
            } else {
                holder.time_tv.setText("Производится доставка");
                holder.pack_b.setVisibility(View.GONE);
            }

            if (order.orderStatus.equals("packed")) {
                holder.pack_b.setVisibility(View.GONE);
            }
        }
    }

    private int getRandomArbitrary(int min, int max) {
        return (int) (new Random().nextFloat() * (max - min) + min);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
