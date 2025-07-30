package br.com.hexagonal.customer_api.adapters.out;

import br.com.hexagonal.customer_api.adapters.out.repository.CustomerEntityRepository;
import br.com.hexagonal.customer_api.adapters.out.repository.entity.CustomerEntity;
import br.com.hexagonal.customer_api.adapters.out.repository.mapper.CustomerEntityMapper;
import br.com.hexagonal.customer_api.application.core.domain.Customer;
import br.com.hexagonal.customer_api.application.ports.out.UpdateCustomerOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateCustomerAdapter implements UpdateCustomerOutputPort {

    @Autowired
    private CustomerEntityRepository customerEntityRepository;

    @Autowired
    private CustomerEntityMapper customerEntityMapper;

    @Override
    public void update(Customer customer) {
        CustomerEntity customerEntity = customerEntityMapper.toCustomerEntity(customer);
        customerEntityRepository.save(customerEntity);
    }
}
