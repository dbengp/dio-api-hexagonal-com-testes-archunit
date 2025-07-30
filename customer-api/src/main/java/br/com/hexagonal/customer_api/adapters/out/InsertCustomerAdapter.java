package br.com.hexagonal.customer_api.adapters.out;

import br.com.hexagonal.customer_api.adapters.out.repository.CustomerEntityRepository;
import br.com.hexagonal.customer_api.adapters.out.repository.entity.CustomerEntity;
import br.com.hexagonal.customer_api.adapters.out.repository.mapper.CustomerEntityMapper;
import br.com.hexagonal.customer_api.application.core.domain.Customer;
import br.com.hexagonal.customer_api.application.ports.out.InsertCustomerOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsertCustomerAdapter implements InsertCustomerOutputPort {

    @Autowired
    private CustomerEntityRepository customerEntityRepository;

    @Autowired
    private CustomerEntityMapper customerEntityMapper;

    @Override
    public void insert(Customer customer) {
        CustomerEntity customerEntity = customerEntityMapper.toCustomerEntity(customer);
        customerEntityRepository.save(customerEntity);
    }
}
