package br.com.hexagonal.customer_api.application.core.usecase;

import br.com.hexagonal.customer_api.application.core.domain.Address;
import br.com.hexagonal.customer_api.application.core.domain.Customer;
import br.com.hexagonal.customer_api.application.ports.in.InsertCustomerInputPort;
import br.com.hexagonal.customer_api.application.ports.out.FindAddressByPostalOutputPort;
import br.com.hexagonal.customer_api.application.ports.out.InsertCustomerOutputPort;
import br.com.hexagonal.customer_api.application.ports.out.QueueCpfForValidationOutputPort;

public class InsertCustomerUseCase implements InsertCustomerInputPort {

    private final FindAddressByPostalOutputPort findAddressByPostalOutputPort;
    private final InsertCustomerOutputPort insertCustomerOutputPort;
    private final QueueCpfForValidationOutputPort queueCpfForValidationOutputPort;

    public InsertCustomerUseCase(
            FindAddressByPostalOutputPort findAddressByPostalOutPutPort,
            InsertCustomerOutputPort insertCustomerOutputPort,
            QueueCpfForValidationOutputPort queueCpfForValidationOutputPort
    ){
        this.findAddressByPostalOutputPort = findAddressByPostalOutPutPort;
        this.insertCustomerOutputPort = insertCustomerOutputPort;
        this.queueCpfForValidationOutputPort = queueCpfForValidationOutputPort;
    }

    @Override
    public void insert(Customer customer, String postal){
        Address address = findAddressByPostalOutputPort.find(postal);
        customer.setAddress(address);
        insertCustomerOutputPort.insert(customer);
        queueCpfForValidationOutputPort.queue(customer.getCpf());
    }

}
