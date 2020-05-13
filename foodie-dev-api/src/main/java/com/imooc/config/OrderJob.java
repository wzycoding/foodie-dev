package com.imooc.config;

import com.imooc.service.OrderService;
import com.imooc.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderJob {

    @Autowired
    private OrderService orderService;

    /**
     * 使用定时任务关闭超期未支付订单，会存在弊端
     *
     * 1、会有时间差
     *   10.39 ，11：00检查，
     *           12点检查，超过一小时多余39分钟
     *           有时间差，不严谨
     *
     * 2、不支持集群
     *   单机没有问题，使用集群后，就会有多个定时任务
     *   解决方案：只是用一台计算机节点，单独用来运行定时任务
     *
     * 3、会对数据库全表搜索，极其影响数据库性能
     *   定时任务，只能适用于小型轻量级项目，传统项目
     *
     *   后续会涉及MQ -> RabbitMQ, RocketMQ, Kafka , ZeroMQ
     *      延时任务（队列）
     *      10：12分下单的，未付款（10）状态，11：12，如果当前状态还是10，则直接关闭订单即可
     */
//    @Scheduled(cron = "0/3 * * * * ?")
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void autoCloseOrder() {

        orderService.closeOrder();
        System.out.println("执行定时任务，当前时间为：" + DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));

    }
}
