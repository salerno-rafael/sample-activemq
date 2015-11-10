package org.sample.amq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Producer {

	@Autowired
	private ConfigurationAmq configurationAmq;
	
	public Producer(ConfigurationAmq configurationAmq){
		this.configurationAmq = configurationAmq;
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigurationAmq.class);
		try {
			new Producer(ctx.getBean(ConfigurationAmq.class)).produceMessage();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void produceMessage() throws JMSException {
		Connection connection = null;
		try {
			connection = configurationAmq.getConnection();
			connection.start();
			sendMessage(connection);
		} finally {
			connection.close();
		}
	}
	
	private void sendMessage(Connection connection) throws JMSException{
		Session session = configurationAmq.getSession(connection);
		MessageProducer producer = messageProducer(session);
		
		producer.send(createMsg("Message # send message asdsadsadasdas",session));
		
		System.out.println("Message Sended...");
		
		producer.close();
		session.close();
	}
	
	private Destination getQueue(Session session) throws JMSException{
		return session.createQueue(configurationAmq.QUEUE);
	}
	
	private TextMessage createMsg(String message,Session session) throws JMSException{
		return session.createTextMessage(message);
	}
	
	private MessageProducer messageProducer(Session session) throws JMSException{
		return session.createProducer(getQueue(session));
	}

}
