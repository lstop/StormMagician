package org.flowable.rocketmq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.consumer.PullStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
 
public class MQPullConsumer extends MQClient{
	private Logger logger = Logger.getLogger(getClass());
	private static final Map<MessageQueue,Long> OFFSE_TABLE = new HashMap<MessageQueue,Long>();
	private DefaultMQPullConsumer consumer;
	
	public void pullMessage() throws MQClientException {
		// 从指定topic中拉取所有消息队列
		Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues(topicName);
		for(MessageQueue mq:mqs){
			try {
				// 获取消息的offset，指定从store中获取
				long offset = consumer.fetchConsumeOffset(mq,true);
				//
				System.out.println("consumer from the queue:"+mq+":"+offset);
				PullResult pullResult = consumer.pull(mq,"*",offset,1); 
				//putMessageQueueOffset(mq,pullResult.getNextBeginOffset());
				if(pullResult.getPullStatus() == PullStatus.FOUND){
					List<MessageExt> messageExtList = pullResult.getMsgFoundList();
                    for (MessageExt m : messageExtList) {
                        System.out.println(new String(m.getBody()));
                    }
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
 
	// 保存上次消费的消息下标
	private static void putMessageQueueOffset(MessageQueue mq,long nextBeginOffset) {
		OFFSE_TABLE.put(mq, nextBeginOffset);
	}
	
	// 获取上次消费的消息的下标
	private static Long getMessageQueueOffset(MessageQueue mq) {
		Long offset = OFFSE_TABLE.get(mq);
		if(offset != null){
			return offset;
		}
		return 0l;
	}

	@Override
	public void init() throws Exception {
		logger.info("开始启动消息消费者 服务。。。");
		consumer = new DefaultMQPullConsumer(consumerGroupName);
		consumer.setNamesrvAddr(nameServerAddr);
		consumer.start();
		logger.info("消息消费者服务启动成功");
	}

	@Override
	public void distroy() throws Exception {
		logger.info("开始关闭消息消费者 服务。。。");
		consumer.shutdown();
		logger.info("消息消费者服务已关闭");
	}
	
 
}