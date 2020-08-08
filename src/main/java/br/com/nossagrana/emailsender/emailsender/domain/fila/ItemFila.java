package br.com.nossagrana.emailsender.emailsender.domain.fila;

import java.util.List;

/**
 *  Representa um item da fila.
 */
public class ItemFila {
    private String destinatario;
    private String nome;

    public ItemFila() {
    }

    public ItemFila(String destinatario, String nome, String tipo) {
        this.destinatario = destinatario;
        this.nome = nome;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}