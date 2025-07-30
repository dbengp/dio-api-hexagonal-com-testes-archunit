package br.com.hexagonal.customer_api.application.core.usecase;

import br.com.hexagonal.customer_api.application.core.domain.Address;
import br.com.hexagonal.customer_api.application.core.domain.Customer;
import br.com.hexagonal.customer_api.application.ports.in.FindCustomerByIdInputPort;
import br.com.hexagonal.customer_api.application.ports.in.UpdateCustomerInputPort;
import br.com.hexagonal.customer_api.application.ports.out.FindAddressByPostalOutputPort;
import br.com.hexagonal.customer_api.application.ports.out.UpdateCustomerOutputPort;

public class UpdateCustomerUseCase implements UpdateCustomerInputPort {

    private final FindCustomerByIdInputPort findCustomerByIdInputPort;
    private final FindAddressByPostalOutputPort findAddressByPostalOutputPort;
    private final UpdateCustomerOutputPort updateCustomerOutputPort;

    public UpdateCustomerUseCase(
            FindCustomerByIdInputPort findCustomerByIdInputPort,
            FindAddressByPostalOutputPort findAddressByPostalOutputPort,
            UpdateCustomerOutputPort updateCustomerOutputPort
    ){
        this.findCustomerByIdInputPort = findCustomerByIdInputPort;
        this.findAddressByPostalOutputPort = findAddressByPostalOutputPort;
        this.updateCustomerOutputPort = updateCustomerOutputPort;
    }

    @Override
    public void update(Customer customer, String postal){
        findCustomerByIdInputPort.find(customer.getId());
        Address address = findAddressByPostalOutputPort.find(postal);
        customer.setAddress(address);
        updateCustomerOutputPort.update(customer);
    }

}
