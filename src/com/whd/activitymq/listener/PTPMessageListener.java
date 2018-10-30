package com.whd.activitymq.listener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/** 
 * @ClassName: PTPListener 
 * @Description: 点对点模式:监听器 
 * @author HongDa
 * @date 2018年10月18日 下午3:53:52 
 */
public class PTPMessageListener implements MessageListener{

	@Override
	public void onMessage(Message message) {

		try {
			if(message != null){
				if (message instanceof TextMessage) {
					TextMessage msg = (TextMessage) message;
					if (msg != null)
						System.out.println("PTPListenerConsumer consume : " + msg.getText());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
