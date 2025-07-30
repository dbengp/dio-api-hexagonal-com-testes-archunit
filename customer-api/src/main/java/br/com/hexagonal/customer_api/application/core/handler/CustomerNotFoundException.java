package br.com.hexagonal.customer_api.application.core.handler;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String id) {
        super("Cliente com ID: { " + id + " } não encontrado");
    }
}
