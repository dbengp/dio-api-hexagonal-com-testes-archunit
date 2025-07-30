package br.com.hexagonal.customer_api.application.core.usecase;

import br.com.hexagonal.customer_api.application.core.domain.Customer;
import br.com.hexagonal.customer_api.application.core.handler.CustomerNotFoundException;
import br.com.hexagonal.customer_api.application.ports.in.FindCustomerByIdInputPort;
import br.com.hexagonal.customer_api.application.ports.out.FindCustomerByIdOutputPort;

public class FindCustomerByIdUseCase implements FindCustomerByIdInputPort {

    private final FindCustomerByIdOutputPort findCustomerByIdOutputPort;

    public FindCustomerByIdUseCase(FindCustomerByIdOutputPort findCustomerByIdOutputPort){
        this.findCustomerByIdOutputPort = findCustomerByIdOutputPort;
    }

    @Override
    public Customer find(String id){
        return findCustomerByIdOutputPort.find(id).orElseThrow(
                () -> new CustomerNotFoundException(id)
        );
    }
}
