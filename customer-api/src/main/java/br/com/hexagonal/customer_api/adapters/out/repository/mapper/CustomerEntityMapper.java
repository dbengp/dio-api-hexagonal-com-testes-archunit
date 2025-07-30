package br.com.hexagonal.customer_api.adapters.out.repository.mapper;

import br.com.hexagonal.customer_api.adapters.out.repository.entity.CustomerEntity;
import br.com.hexagonal.customer_api.application.core.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerEntityMapper {

    @Autowired
    private AddressEntityMapper addressEntityMapper;

    public CustomerEntity toCustomerEntity(Customer customer){

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customer.getId());
        customerEntity.setName(customer.getName());
        customerEntity.setCpf(customer.getCpf());
        customerEntity.setIsCpf(customer.getIsCpf());
        Optional.ofNullable(customer.getAddress())
                .ifPresent(address -> customerEntity.setAddress(addressEntityMapper.toAddressEntity(address)));
        return customerEntity;
    }

    public Customer toCustomer(CustomerEntity customerEntity){
        Customer customer = new Customer();
        customer.setId(customerEntity.getId());
        customer.setName(customerEntity.getName());
        customer.setCpf(customerEntity.getCpf());
        customer.setIsCpf(customerEntity.getIsCpf());
        Optional.ofNullable(customerEntity.getAddress())
                .ifPresent(addressEntity -> customer.setAddress(addressEntityMapper.toAddress(addressEntity)));
        return customer;
    }


}
