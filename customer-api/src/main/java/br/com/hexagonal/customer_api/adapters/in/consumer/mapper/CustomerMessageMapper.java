package br.com.hexagonal.customer_api.adapters.in.consumer.mapper;


import br.com.hexagonal.customer_api.adapters.in.consumer.message.CustomerMessage;
import br.com.hexagonal.customer_api.application.core.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMessageMapper {

    public Customer toCustomer(CustomerMessage customerMessage){
        Customer customer = new Customer();
        customer.setId(customerMessage.getId());
        customer.setName(customerMessage.getName());
        customer.setCpf(customerMessage.getCpf());
        customer.setIsCpf(customerMessage.getIsCpf());
        return customer;

    }
}
