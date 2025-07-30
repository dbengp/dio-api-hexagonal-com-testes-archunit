package br.com.hexagonal.customer_api.application.ports.out;

import br.com.hexagonal.customer_api.application.core.domain.Customer;

public interface UpdateCustomerOutputPort {

    void update(Customer customer);
}
