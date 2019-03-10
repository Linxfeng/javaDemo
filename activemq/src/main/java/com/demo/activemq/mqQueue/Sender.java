package com.demo.activemq.mqQueue;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 点到点模式 --发送者
 * 
 * @author Apple
 *
 */
public class Sender {

	private static final String url = "tcp://localhost:61616";
	private static final String QUEUE_NAME = "FirstQueue";

	public static void main(String[] args) {
		// ConnectionFactory ：连接工厂，JMS用它创建连接
		ConnectionFactory connectionFactory;
		// Connection ：JMS 客户端到JMS Provider 的连接
		Connection connection = null;
		// Session: 一个发送或接收消息的线程
		Session session;
		// Destination ：消息的目的地;消息发送给谁.
		Destination destination;
		// MessageProducer：消息发送者
		MessageProducer producer;
		// TextMessage message;
		// 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
		connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, url);
		try {
			// 构造从工厂得到连接对象
			connection = connectionFactory.createConnection();
			// 启动
			connection.start();
			// 获取操作连接
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			// 获取session注意参数值是一个服务器的queue/topic
			// destination = session.createTopic(QUEUE_NAME);
			destination = session.createQueue(QUEUE_NAME);
			// 得到消息生成者【发送者】
			producer = session.createProducer(destination);
			// 持久化的设置，此处学习，实际根据项目决定
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			// 构造消息，此处写死，项目就是参数，或者方法获取
			sendMessage(session, producer);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
			} catch (Throwable ignore) {
			}
		}
	}

	public static void sendMessage(Session session, MessageProducer producer) throws Exception {
		TextMessage message = session.createTextMessage("ActiveMq发送的消息：测试！");
		// 发送消息到目的地方
		System.out.println("ActiveMq发送了消息");
		producer.send(message);
	}
}
