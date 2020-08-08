package br.com.nossagrana.emailsender.emailsender.support;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CloudAmqpConnection {
    private CachingConnectionFactory connectionFactory;
    private String hostname;
    private String username;
    private String password;

    public CloudAmqpConnection(
            @Value("${mailsender.cloudamqp.hostname}") String hostname,
            @Value("${mailsender.cloudamqp.username}") String username,
            @Value("${mailsender.cloudamqp.password}") String password
    ) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
    }

    public  CachingConnectionFactory getConnection(){

        if(connectionFactory == null){
            connectionFactory = new CachingConnectionFactory(hostname);
            connectionFactory.setUsername(username);
            connectionFactory.setPassword(password);
            connectionFactory.setVirtualHost(username);

            //Recommended settings
            connectionFactory.setRequestedHeartBeat(30);
            connectionFactory.setConnectionTimeout(30000);
        }

        return connectionFactory;
    }

}