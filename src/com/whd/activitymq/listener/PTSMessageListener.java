package com.whd.activitymq.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/** 
 * @ClassName: PTSMessageListener 
 * @Description: 发布订阅模式监听器
 * @author HongDa
 * @date 2018年10月18日 下午6:40:04 
 */
public class PTSMessageListener implements MessageListener{
	
	private String name;
	
    public PTSMessageListener(String name) {
		this.name = name;
	}

	@Override
	public void onMessage(Message message) {
		try {
			if (message != null) {
				if (message instanceof TextMessage) {
					TextMessage msg = (TextMessage) message;
					if (msg != null) {
						System.out.println("PTSListener[" + name + "] listen : " + msg.getText());
					}
				}
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
