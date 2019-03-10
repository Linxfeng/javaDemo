package com.demo.activemq.mqTopic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 发布/订阅模式 --发布者
 * 
 * @author Apple
 *
 */
public class Publish {
	public static void main(String[] args) {
		ConnectionFactory connectionFactory = null;
		Connection conn = null;
		Session session = null;
		Topic topic = null;
		MessageProducer messageProducer = null;
		try {
			// 创建工厂
			// ActiveMQConnection.DEFAULT_USER 默认null
			// ActiveMQConnection.DEFAULT_PASSWORD 默认null
			// ActiveMQConnection.DEFAULT_BROKER_URL
			// 默认failover://tcp://localhost:61616
			connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
					ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
			// 创建连接
			conn = connectionFactory.createConnection();
			// 启动连接
			conn.start();
			// 创建会话 createSession(true, Session.AUTO_ACKNOWLEDGE); true 表示开启事务
			// Session.AUTO_ACKNOWLEDGE 消息模式
			session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
			// 创建Topic
			topic = session.createTopic("myTopic1");
			// 创建消息生产者
			messageProducer = session.createProducer(topic);
			// 创建消息
			TextMessage message = session.createTextMessage();
			message.setText("我是myTopic1发布者发布的消息");
			// 发送消息
			messageProducer.send(message);
			// 提交事务
			session.commit();
			System.out.println("发布OK");
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			try {
				session.close();
				conn.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}

	}
}
