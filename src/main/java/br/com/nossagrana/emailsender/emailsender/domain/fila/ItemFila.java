package br.com.nossagrana.emailsender.emailsender.domain.fila;

import java.util.List;

/**
 *  Representa um item da fila.
 */
public class ItemFila {
    private List<String> destinatarios;
    private String nome;
    private String tipo;

    public ItemFila() {
    }

    public ItemFila(List<String> destinatario, String nome, String tipo) {
        this.destinatarios = destinatario;
        this.nome = nome;
        this.tipo = tipo;
    }

    public List<String> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List<String> destinatario) {
        this.destinatarios = destinatario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}