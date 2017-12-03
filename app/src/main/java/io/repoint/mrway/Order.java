package io.repoint.mrway;

import java.util.List;

/**
 * Created by mishu on 03.12.2017.
 */

public class Order {

    public String id;
    public int code;
    public boolean isCompleted;
    public String orderStatus;
    public String deliveryStatus;
    public double cost;
    public List<OrderLine> lines;
}
