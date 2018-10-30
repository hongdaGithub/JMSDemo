package com.whd.activitymq.producer;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.whd.activitymq.provider.JMSProvider;

/** 
 * @ClassName: PTSProducer 
 * @Description: 发布订阅模式(Publish To Subscribe):消息生产者 
 * @author HongDa
 * @date 2018年10月18日 下午5:24:36 
 */
public class PTSMessageProducer {
	
	private void produce(){
		
		Connection connection = JMSProvider.getConnection();
		Session session = null;
		MessageProducer producer = null;
		
		try {
			connection.start();
			session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createTopic("FirstTopic");
			producer = session.createProducer(destination);
			
			publish(session, producer);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JMSProvider.close(producer, session, connection);
		}
	}
	
	public static void publish(Session session,MessageProducer producer) throws Exception{
		
		int count = 0;
		
		while(true){
			String msg = "ActivityMQ PTSMessage " + count++;
			TextMessage message = session.createTextMessage(msg);
			System.out.println("PTSProducer produce : " + msg);
			producer.send(message);
			session.commit();
			Thread.sleep(5000);
		}
	}
	
	public static void main(String[] args){
		PTSMessageProducer producer = new PTSMessageProducer();
		producer.produce();
	}
}
