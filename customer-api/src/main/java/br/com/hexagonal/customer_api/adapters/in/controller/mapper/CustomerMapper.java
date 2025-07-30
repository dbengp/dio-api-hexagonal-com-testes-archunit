package br.com.hexagonal.customer_api.adapters.in.controller.mapper;

import br.com.hexagonal.customer_api.adapters.in.controller.request.CustomerRequest;
import br.com.hexagonal.customer_api.adapters.in.controller.response.CustomerResponse;
import br.com.hexagonal.customer_api.application.core.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CustomerMapper {

    @Autowired
    private AddressMapper addressMapper;

    public Customer toCustomer(CustomerRequest customerRequest){

        Customer customer = new Customer();
        customer.setName(customerRequest.name());
        customer.setCpf(customerRequest.cpf());
        customer.setIsCpf(true);

        return customer;
    }

    public CustomerResponse toCustomerReponse(Customer customer){
        return new CustomerResponse(
                customer.getName(),
                customer.getCpf(),
                addressMapper.toAddressResponse(customer.getAddress())
        );
    }
}
