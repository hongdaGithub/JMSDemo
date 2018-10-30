package com.whd.activitymq.consumer;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import com.whd.activitymq.listener.PTSMessageListener;
import com.whd.activitymq.provider.JMSProvider;

/** 
 * @ClassName: PTSConsumerOne 
 * @Description: 发布订阅模式:消息消费者1 
 * @author HongDa
 * @date 2018年10月18日 下午6:37:36 
 */
public class PTSConsumerOne {
	
	private void consume(){
		
		Connection connection = JMSProvider.getConnection();
		Session session = null;
		MessageConsumer consumer = null;
		
		try {
			connection.start();
			session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createTopic("FirstTopic");
			consumer = session.createConsumer(destination);
			
			consumer.setMessageListener(new PTSMessageListener("consumer1"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		PTSConsumerOne consumer = new PTSConsumerOne();
		consumer.consume();
	}
}
