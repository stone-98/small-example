package org.example.rocketmq.base.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * 消息的接受者
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        //1.创建消费者Consumer，制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");
        //2.指定Nameserver地址
        consumer.setNamesrvAddr("192.168.25.135:9876;192.168.25.138:9876");
        //3.订阅主题Topic和Tag
        consumer.subscribe("base", "*");

        //设定消费模式：负载均衡|广播模式
        consumer.setMessageModel(MessageModel.BROADCASTING);

        //4.设置回调函数，处理消息
        //接受消息内容
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            for (MessageExt msg : msgs) {
                System.out.println("consumeThread=" + Thread.currentThread().getName() + "," + new String(msg.getBody()));
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        //5.启动消费者consumer
        consumer.start();

    }
}
