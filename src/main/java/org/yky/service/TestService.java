package org.yky.service;

import dd.springboot.demo.models.YkyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;
import org.yky.dao.User;
import org.yky.dao.YkyUserDao;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

@Configuration
@EnableScheduling
public class TestService implements SchedulingConfigurer {


    @Resource
    private EchoEventPublisher echoEventPublisher;
    @Resource
    private ScheduledExecutorService scheduledExecutorService;
    @Resource(name = "aaa")
    private ScheduledExecutorService aaa;

    /**
     * 心跳的线程数
     */
    private int scheduledThreadPool = 1;

    private Queue echoQueue = new LinkedBlockingQueue(1);

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addFixedRateTask(this::echo, 2000);
        scheduledTaskRegistrar.setScheduler(scheduledExecutorService);
    }

    @Bean(destroyMethod = "shutdown")
    public ScheduledExecutorService scheduledExecutorService() {
//        System.out.println(111111);
        return Executors.newScheduledThreadPool(scheduledThreadPool); // scheduledThreadPool个线程来处理。
    }

    @Bean(name = "aaa")
    public ScheduledExecutorService aaa() {
//        System.out.println(222222);
        return Executors.newScheduledThreadPool(scheduledThreadPool); // scheduledThreadPool个线程来处理。
    }

    /**
     * 每分钟扫描一次，
     * 如果队列中没有值，则发生一次echo，同时往队列中插入一条记录，认为此时网络连接正常
     * 如果队列中有值，则说明上次心跳未响应，认为此时网络异常
     */
    public void echo() {
        if (echoQueue.isEmpty()) {
//            System.out.println("echo");
            echoQueue.add(1);
            echoEventPublisher.publisher(new EchoEvent(this, "222"));

        } else {
//            System.out.println("echo end");
            echoQueue.poll();

        }
    }

    @EventListener(EchoEvent.class)
    public void handlerEchoCheck(EchoEvent event) {
        String isId = event.getIsId();
        aaa.schedule(() -> {}, 1000, TimeUnit.MILLISECONDS);


    }

}