package br.com.hexagonal.customer_api.application.ports.out;

public interface QueueCpfForValidationOutputPort {

    void queue(String cpf);
}
