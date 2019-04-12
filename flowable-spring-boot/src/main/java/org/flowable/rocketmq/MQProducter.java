package org.flowable.rocketmq;

import org.apache.log4j.Logger;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import com.alibaba.fastjson.JSON;


public class MQProducter extends MQClient{
	private Logger logger = Logger.getLogger(getClass());
	private DefaultMQProducer producter;

	@Override
	public void init() throws Exception{
		logger.info("开始启动消息生产者服务。。。");
		producter = new DefaultMQProducer(producterGroupName);
		producter.setNamesrvAddr(nameServerAddr);
		producter.start();
		logger.info("消息生产者服务启动成功。");
	}

	@Override
	public void distroy(){
		logger.info("开始改关闭消息生产者服务。。。");
		producter.shutdown();
		logger.info("消息生产者服务已关闭。");
	}
	public DefaultMQProducer getProducter() {
		return producter;
	}
	public SendResult sendMessage(TaskProperties taskProperties){
		Message msg 		= null;
		SendResult result 	= null;
		try {
			msg = new Message(topicName,null,
					JSON.toJSONString(taskProperties).getBytes());
			
			result = this.getProducter().send(msg);
			if(result.getSendStatus()!=SendStatus.SEND_OK)
				throw new Exception("消息发送失败！");
		} catch (MQClientException e) {
			e.printStackTrace();
		} catch (RemotingException e) {
			e.printStackTrace();
		} catch (MQBrokerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}
	
	public SendResult sendMessage(TaskProperties taskProperties,String tags){
		Message msg 		= null;
		SendResult result 	= null;
		try {
			msg = new Message(topicName,tags,
					JSON.toJSONString(taskProperties).getBytes());
			result = this.getProducter().send(msg);
			if(result.getSendStatus()!=SendStatus.SEND_OK)
				throw new Exception("消息发送失败！");
		} catch (MQClientException e) {
			e.printStackTrace();
		} catch (RemotingException e) {
			e.printStackTrace();
		} catch (MQBrokerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}
	
	
}
