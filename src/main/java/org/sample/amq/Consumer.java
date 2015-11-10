package org.sample.amq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Consumer {

	@Autowired
	private ConfigurationAmq configurationAmq;
	
	public Consumer(ConfigurationAmq configurationAmq){
		this.configurationAmq = configurationAmq;
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigurationAmq.class);
		try {
			new Consumer(ctx.getBean(ConfigurationAmq.class)).receiver();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void receiver() throws JMSException {
		Connection connection = null;
		try {
			connection = configurationAmq.getConnection();
			connection.start();
			receiveMessage(connection);
		} finally {
			connection.close();
		}
	}

	private void receiveMessage(Connection connection) throws JMSException {
		connection.start();

		Session session = configurationAmq.getSession(connection);
		Destination destination = getQueue(session);
		MessageConsumer consumer = session.createConsumer(destination);

		waitMessage(consumer);

		consumer.close();
		session.close();
	}

	private void waitMessage(MessageConsumer consumer) throws JMSException {
		while (true) {
			Message message = consumer.receive(configurationAmq.TIMEOUT);
			if (message == null) break;
			System.out.println("Message Received: " + ((TextMessage) message).getText());
		}
	}

	private Destination getQueue(Session session) throws JMSException {
		return session.createQueue(configurationAmq.QUEUE + "?consumer.exclusive=true");
	}

	
}
