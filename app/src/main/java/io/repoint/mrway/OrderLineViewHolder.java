package io.repoint.mrway;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by mishu on 03.12.2017.
 */

public class OrderLineViewHolder extends RecyclerView.ViewHolder {

    public final TextView name_tv;
    public final TextView quantity_tv;
    public final TextView cost_tv;
    public final TextView sum_tv;

    public OrderLineViewHolder(View itemView) {
        super(itemView);
        name_tv = itemView.findViewById(R.id.name_tv);
        quantity_tv = itemView.findViewById(R.id.quantity_tv);
        cost_tv = itemView.findViewById(R.id.cost_tv);
        sum_tv = itemView.findViewById(R.id.sum_tv);
    }
}
