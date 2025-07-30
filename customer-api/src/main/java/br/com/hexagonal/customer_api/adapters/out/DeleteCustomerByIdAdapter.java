package br.com.hexagonal.customer_api.adapters.out;

import br.com.hexagonal.customer_api.adapters.out.repository.CustomerEntityRepository;
import br.com.hexagonal.customer_api.application.ports.out.DeleteCustomerByIdOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteCustomerByIdAdapter implements DeleteCustomerByIdOutputPort {

    @Autowired
    private CustomerEntityRepository customerEntityRepository;

    @Override
    public void delete(String id) {
        customerEntityRepository.deleteById(id);
    }
}
