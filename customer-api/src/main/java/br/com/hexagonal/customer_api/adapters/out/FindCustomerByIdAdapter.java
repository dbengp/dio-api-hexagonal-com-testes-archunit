package br.com.hexagonal.customer_api.adapters.out;

import br.com.hexagonal.customer_api.adapters.out.repository.CustomerEntityRepository;
import br.com.hexagonal.customer_api.adapters.out.repository.mapper.CustomerEntityMapper;
import br.com.hexagonal.customer_api.application.core.domain.Customer;
import br.com.hexagonal.customer_api.application.ports.out.FindCustomerByIdOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindCustomerByIdAdapter implements FindCustomerByIdOutputPort {

    @Autowired
    private CustomerEntityRepository customerEntityRepository;

    @Autowired
    private CustomerEntityMapper customerEntityMapper;

    @Override
    public Optional<Customer> find(String id) {
        return customerEntityRepository.findById(id).map(customerEntityMapper::toCustomer);
    }
}
