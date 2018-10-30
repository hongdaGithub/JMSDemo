package com.whd.activitymq.consumer;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.whd.activitymq.provider.JMSProvider;

/** 
 * @ClassName: PTPReceiveConsumer 
 * @Description: 点对点模式:Receive方式消费者
 * @author HongDa
 * @date 2018年10月18日 下午2:48:20 
 */
public class PTPReceiveConsumer {
	
	/**
	 * @Description 消费消息
	 * @author HongDa
	 * @version 1.1.0
	 * @date 2018年10月18日 下午3:41:47
	 * @throws
	 */
	private void consume(){
		//获取连接
		Connection connection = JMSProvider.getConnection();
		Session session = null;
		MessageConsumer consumer = null;
		
		try {
			//启动连接
			connection.start();
			//获取会话
			session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			//创建目的地
			Destination destination = session.createQueue("FirstQueue");
			//创建消费者
			consumer = session.createConsumer(destination);
			//消费消息
			receiveMessage(consumer);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JMSProvider.close(consumer, session, connection);
		}
	}
	
	/**
	 * @Description 消费者接受消息
	 * @author HongDa
	 * @version 1.1.0
	 * @date 2018年10月18日 下午3:40:23
	 * @param consumer
	 * @throws Exception
	 * @throws
	 */
	public static void receiveMessage(MessageConsumer consumer) throws Exception{
		
		while(true){
			TextMessage msg = (TextMessage)consumer.receive();
			
			if(msg != null)
				System.out.println("PTPReceiveConsumer consume : " + msg.getText());
			else
				break;
		}
	}
	
	public static void main(String[] args){
		PTPReceiveConsumer consumer = new PTPReceiveConsumer();
		consumer.consume();
	}
}
