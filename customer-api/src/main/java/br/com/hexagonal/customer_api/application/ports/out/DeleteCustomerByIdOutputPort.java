package br.com.hexagonal.customer_api.application.ports.out;

public interface DeleteCustomerByIdOutputPort {

    void delete(String id);
}
