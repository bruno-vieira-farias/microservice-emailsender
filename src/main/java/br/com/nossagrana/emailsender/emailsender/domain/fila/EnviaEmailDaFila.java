package br.com.nossagrana.emailsender.emailsender.domain.fila;

import br.com.nossagrana.emailsender.emailsender.domain.envio.EnviaEmailService;
import br.com.nossagrana.emailsender.emailsender.support.CloudAmqpConnection;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EnviaEmailDaFila {
    private RabbitTemplate template;
    private EnviaEmailService emailService;
    private ObjectMapper objectMapper;
    private String queueName;

    @Autowired
    public EnviaEmailDaFila(
            EnviaEmailService emailService,
            CloudAmqpConnection cloudAmqpConnection,
            @Value("${mailsender.cloudamqp.queue-name}") String queueName

    ) {
        this.emailService = emailService;
        this.template = new RabbitTemplate(cloudAmqpConnection.getConnection());
        this.objectMapper = new ObjectMapper();
        this.queueName = queueName;
    }

    /**
     * Verifica se existe e-mail para envio na fila e envia caso o encontre.
     */
    public void enviaEmailDisponivelNaFila() {
        Message itemFila = template.receive(queueName);

        if (itemFila == null) {
            return;
        }
        byte[] json = itemFila.getBody();

        try {

            ItemFila item = objectMapper.readValue(json, ItemFila.class);
            emailService.enviaEmailSolicitacaoEmprestimo(item.getDestinatario(), item.getNome());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}