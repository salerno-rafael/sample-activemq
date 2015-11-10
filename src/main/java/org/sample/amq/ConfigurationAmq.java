package org.sample.amq;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationAmq {

	public final String BROKER_HOST = "tcp://localhost:%d";
	public final int BROKER_PORT = 61613;
	public final String BROKER_URL = String.format(BROKER_HOST, BROKER_PORT);
	public final Boolean NON_TRANSACTED = false;
	public final String USER = "admin";
	public final String PASSWORD = "password";
	public final String QUEUE = "test-queue";
	public final long TIMEOUT = 20000;

	public Connection getConnection() throws JMSException {
		return connectionFactory().createConnection();
	}

	public Session getSession(Connection connection) throws JMSException {
		return connection.createSession(NON_TRANSACTED, Session.AUTO_ACKNOWLEDGE);
	}

	private ActiveMQConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory(USER, PASSWORD, BROKER_URL);
	}

}
