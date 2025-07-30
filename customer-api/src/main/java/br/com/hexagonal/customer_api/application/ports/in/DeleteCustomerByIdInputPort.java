package br.com.hexagonal.customer_api.application.ports.in;

public interface DeleteCustomerByIdInputPort {

    void delete(String id);
}
