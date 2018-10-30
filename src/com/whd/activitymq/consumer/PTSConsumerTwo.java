package com.whd.activitymq.consumer;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import com.whd.activitymq.listener.PTSMessageListener;
import com.whd.activitymq.provider.JMSProvider;

public class PTSConsumerTwo {
	private void consume(){
		
		Connection connection = JMSProvider.getConnection();
		Session session = null;
		MessageConsumer consumer = null;
		
		try {
			connection.start();
			session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createTopic("FirstTopic");
			consumer = session.createConsumer(destination);
			
			consumer.setMessageListener(new PTSMessageListener("consumer2"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		PTSConsumerTwo consumer = new PTSConsumerTwo();
		consumer.consume();
	}
}
