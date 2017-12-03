package io.repoint.mrway;

import android.content.Intent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ru.evotor.framework.core.IntegrationException;
import ru.evotor.framework.core.IntegrationManagerCallback;
import ru.evotor.framework.core.IntegrationManagerFuture;
import ru.evotor.framework.core.action.command.open_receipt_command.OpenSellReceiptCommand;
import ru.evotor.framework.core.action.event.receipt.changes.position.PositionAdd;
import ru.evotor.framework.receipt.Position;

/**
 * Created by mishu on 03.12.2017.
 */

public class OrderIt {

    public static void orderIt(Order order, final MainActivity context) {
        List<PositionAdd> list = new ArrayList<>();
        for (OrderLine line : order.lines) {
            list.add(new PositionAdd(Position.Builder.newInstance(
                    UUID.randomUUID().toString(),
                    line.productUuid,
                    line.name,
                    "measureName",
                    0,
                    new BigDecimal(line.cost),
                    new BigDecimal(line.quantity)
            ).build()));
        }

        new OpenSellReceiptCommand(list, null).process(
                context,
                new IntegrationManagerCallback() {
                    @Override
                    public void run(IntegrationManagerFuture integrationManagerFuture) {
                        try {
                            IntegrationManagerFuture.Result result = integrationManagerFuture.getResult();
                            if (result.getType() == IntegrationManagerFuture.Result.Type.OK) {
                                context.startActivity(new Intent("evotor.intent.action.payment.SELL"));
                            }
                        } catch (IntegrationException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}