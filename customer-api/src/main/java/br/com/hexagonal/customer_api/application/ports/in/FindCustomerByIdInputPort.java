package br.com.hexagonal.customer_api.application.ports.in;

import br.com.hexagonal.customer_api.application.core.domain.Customer;

public interface FindCustomerByIdInputPort {

    Customer find(String id);
}
