package com.whd.activitymq.producer;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.whd.activitymq.provider.JMSProvider;

/** 
 * @ClassName: PTPProducer 
 * @Description: 点对点模式(Point To Point):消息生产者
 * @author HongDa
 * @date 2018年10月18日 下午2:46:35 
 */
public class PTPMessageProducer {
	
	private void produce(){
		
		Connection connection = JMSProvider.getConnection();
		Session session = null;
		MessageProducer producer = null;
		
		try {
			//启动连接
			connection.start();
			//创建会话session
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			//创建目的地:点对点模式成为Queue
			Destination destination = session.createQueue("FirstQueue");
			//创建生产者
			producer = session.createProducer(destination);
			
			//发送消息
			sendMessage(session, producer);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JMSProvider.close(producer, session, connection);
		}
	}
	
	/**
	 * @throws Exception 
	 * @throws JMSException 
	 * @Description 发送消息
	 * @author HongDa
	 * @version 1.1.0
	 * @date 2018年10月18日 下午3:24:57
	 * @param session
	 * @param producer
	 * @throws
	 */
	public static void sendMessage(Session session,MessageProducer producer) throws Exception{
		
		int count = 0;
		
		while(true){
			String msg = "ActivityMQ PTPMessage " + count++;
			TextMessage message = session.createTextMessage(msg);
			System.out.println("PTPProducer produce : " + msg);
			producer.send(message);
			session.commit();
			Thread.sleep(5000);
		}
	}
	
	public static void main(String[] args){
		PTPMessageProducer producer = new PTPMessageProducer();
		producer.produce();
	}
}
