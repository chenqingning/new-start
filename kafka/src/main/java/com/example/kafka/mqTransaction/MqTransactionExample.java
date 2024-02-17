package com.example.kafka.mqTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class MqTransactionExample {
    private OrderService orderService;
    private MqService mqService;
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Transactional
    public void order(OrderDTO orderDTO) {
        orderService.createOrder(orderDTO);
        mqService.createMessage(orderDTO);
        //主要不要把发布消息放在事务处理里面，避免事务超时失败
        mqService.send(orderDTO);
    }

    /**
     * 编程式事务
     */
    public void order1(OrderDTO orderDTO) {
        //1没有结果返回
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                orderService.createOrder(orderDTO);
                mqService.createMessage(orderDTO);
            }
        });
        //2有返回值
        transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    orderService.createOrder(orderDTO);
                    mqService.createMessage(orderDTO);
                    return true;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    return false;
                }
            }
        });

        mqService.send(orderDTO);
        mqService.updateSuccess(orderDTO);
    }
}
