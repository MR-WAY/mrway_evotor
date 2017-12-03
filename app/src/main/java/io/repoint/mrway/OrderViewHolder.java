package io.repoint.mrway;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mishu on 03.12.2017.
 */

public class OrderViewHolder extends RecyclerView.ViewHolder {

    public final View root;
    public final View indicator_v;
    public final TextView number_tv;
    public final TextView time_tv;
    public final TextView cost_tv;
    public final RecyclerView lines_rv;
    public final Button pack_b;

    public OrderViewHolder(View itemView) {
        super(itemView);
        root = itemView.findViewById(R.id.root);
        indicator_v = itemView.findViewById(R.id.indicator_v);
        number_tv = itemView.findViewById(R.id.number_tv);
        time_tv = itemView.findViewById(R.id.time_tv);
        cost_tv = itemView.findViewById(R.id.cost_tv);
        lines_rv = itemView.findViewById(R.id.lines_rv);
        pack_b = itemView.findViewById(R.id.pack_b);
    }
}
