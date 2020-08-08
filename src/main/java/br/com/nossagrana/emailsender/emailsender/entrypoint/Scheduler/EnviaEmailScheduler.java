package br.com.nossagrana.emailsender.emailsender.entrypoint.Scheduler;

import br.com.nossagrana.emailsender.emailsender.domain.fila.EnviaEmailDaFila;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EnviaEmailScheduler {
    private EnviaEmailDaFila enviaEmailDaFila;

    @Autowired
    public EnviaEmailScheduler(EnviaEmailDaFila enviaEmailDaFila) {
        this.enviaEmailDaFila = enviaEmailDaFila;
    }

    /* Tenta enviar a cada 5 segundos. */
    @Scheduled(cron = "*/5 * * * * ?")
    public void execute() {
        enviaEmailDaFila.enviaEmailDisponivelNaFila();
    }

}