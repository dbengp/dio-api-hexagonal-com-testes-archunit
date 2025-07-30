package br.com.hexagonal.customer_api.application.ports.in;

import br.com.hexagonal.customer_api.application.core.domain.Customer;

public interface InsertCustomerInputPort {

    void insert(Customer customer, String postal);
}
