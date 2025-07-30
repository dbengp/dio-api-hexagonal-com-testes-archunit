package br.com.hexagonal.customer_api.application.ports.out;

import br.com.hexagonal.customer_api.application.core.domain.Customer;

import java.util.Optional;

public interface FindCustomerByIdOutputPort {

    Optional<Customer> find(String id);
}
