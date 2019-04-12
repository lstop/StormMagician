package org.flowable.rocketmq;

import org.apache.log4j.Logger;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;


public class MQPushConsumer extends MQClient{
	private Logger logger = Logger.getLogger(getClass());
	private DefaultMQPushConsumer consumer;

	@Override
	public void init() throws Exception{
		logger.info("开始启动消息消费者 服务。。。");
		consumer = new DefaultMQPushConsumer(consumerGroupName);
		consumer.setNamesrvAddr(nameServerAddr);
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		consumer.subscribe(topicName, "*");
		consumer.registerMessageListener(new MessageListener());
		consumer.start();
		logger.info("消息消费者服务启动成功");
	}
	
	@Override
	public void distroy(){
		logger.info("开始关闭消息消费者 服务。。。");
		consumer.shutdown();
		logger.info("消息消费者服务已关闭");
	}
	public DefaultMQPushConsumer getConsumer() {
		return consumer;
	}
	
}
