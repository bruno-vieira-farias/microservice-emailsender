package br.com.nossagrana.emailsender.emailsender.domain.envio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EnviaEmailService {
    private Session session;
    private String from;

    @Autowired
    public EnviaEmailService(
            @Value("${mailsender.smtp.server}") String server,
            @Value("${mailsender.smtp.port}") String port,
            @Value("${mailsender.smtp.ssl}") String ssl,
            @Value("${mailsender.from}") String from,
            @Value("${mailsender.password}") String password
    ) {
        this.from = from;
        setup(server, port, ssl, password,from);
    }

    public void enviaEmailSolicitacaoEmprestimo(String destinatario, String nome){
        enviaEmail("Sua solicitação de emprestimo foi confirmada." ,
                "Olá "+ nome + ", Sua solicitação de emprestimo foi realizada. Agora  deixe o resto a gente ;)",
                destinatario);
    }

    private void enviaEmail(String assunto, String mensagem, String destinatario) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));

            Address[] toUser = InternetAddress.parse(destinatario);

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(assunto);
            message.setText(mensagem);

            Transport.send(message);
            System.out.println("Email enviado com sucesso!!!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private void setup(String server, String port, String ssl, String password,String from) {
        Properties props = new Properties();
        props.put("mail.smtp.host", server);
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", ssl);
        props.put("mail.smtp.port", port);

        session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        session.setDebug(true);
    }
}