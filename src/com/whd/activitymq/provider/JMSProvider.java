package com.whd.activitymq.provider;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/** 
 * @ClassName: JMSProvider 
 * @Description: JMS提供者
 * @author HongDa
 * @date 2018年10月18日 下午2:52:43 
 */
public class JMSProvider {
	
	private static ConnectionFactory connectionFactory = null;
	
	private static String userName = ActiveMQConnection.DEFAULT_USER;
	private static String password = ActiveMQConnection.DEFAULT_PASSWORD;
	private static String brokerURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	
	/**
	 * @Description 初始化连接工厂
	 * @author HongDa
	 * @version 1.1.0
	 * @date 2018年10月18日 下午3:04:27
	 * @throws
	 */
	private static void initConnectionFactory(){
		
		connectionFactory = new ActiveMQConnectionFactory(userName, password, brokerURL);
	}
	
	/**
	 * @Description 获取连接
	 * @author HongDa
	 * @version 1.1.0
	 * @date 2018年10月18日 下午3:01:08
	 * @return
	 * @throws
	 */
	public static Connection getConnection(){
		
		if(connectionFactory == null){
			initConnectionFactory();
		}
		
		try {
			Connection connection = connectionFactory.createConnection();
			return connection;
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * @Description 关闭资源
	 * @author HongDa
	 * @version 1.1.0
	 * @date 2018年10月18日 下午3:04:05
	 * @param producer
	 * @param consumer
	 * @param session
	 * @param connection
	 * @throws
	 */
	public static void close(MessageProducer producer, MessageConsumer consumer, Session session,
			Connection connection) {
		
		try {
			
			if (producer != null)
				producer.close();
			
			if(consumer != null)
				consumer.close();
			
			if(session != null)
				session.close();
			
			if(connection != null)
				connection.close();
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(MessageProducer producer, Session session,
			Connection connection) {
		
		try {
			
			if (producer != null)
				producer.close();
			
			if(session != null)
				session.close();
			
			if(connection != null)
				connection.close();
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(MessageConsumer consumer, Session session,
			Connection connection) {
		
		try {
			
			if(consumer != null)
				consumer.close();
			
			if(session != null)
				session.close();
			
			if(connection != null)
				connection.close();
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
