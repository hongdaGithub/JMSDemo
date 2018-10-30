package com.whd.activitymq.consumer;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import com.whd.activitymq.listener.PTPMessageListener;
import com.whd.activitymq.provider.JMSProvider;

/** 
 * @ClassName: PTPListenerConsumer 
 * @Description: 点对点模式:Listener方式 消费者
 * @author HongDa
 * @date 2018年10月18日 下午2:50:29 
 */
public class PTPListenerConsumer {
	
	private void consume(){
		
		Connection connection = JMSProvider.getConnection();
		Session session = null;
		MessageConsumer consumer = null;
		
		try {
			connection.start();
			session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("FirstQueue");
			consumer = session.createConsumer(destination);
			
			consumer.setMessageListener(new PTPMessageListener());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		PTPListenerConsumer consumer = new PTPListenerConsumer();
		consumer.consume();
	}
}
