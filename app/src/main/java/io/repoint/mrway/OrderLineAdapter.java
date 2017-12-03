package io.repoint.mrway;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by mishu on 03.12.2017.
 */

public class OrderLineAdapter extends RecyclerView.Adapter<OrderLineViewHolder> {

    private final List<OrderLine> lines;

    public OrderLineAdapter(Context context, List<OrderLine> lines) {
        this.lines = lines;
    }

    @Override
    public OrderLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order_line, parent, false);
        return new OrderLineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderLineViewHolder holder, int position) {
        OrderLine line = lines.get(position);

        holder.name_tv.setText(line.name);
        holder.quantity_tv.setText(String.valueOf(line.quantity));
        holder.cost_tv.setText(String.valueOf(line.cost));
        holder.sum_tv.setText(String.valueOf(line.quantity * line.cost));
    }

    @Override
    public int getItemCount() {
        return lines.size();
    }
}
