package br.com.hexagonal.customer_api.config;

import br.com.hexagonal.customer_api.adapters.out.DeleteCustomerByIdAdapter;
import br.com.hexagonal.customer_api.application.core.usecase.DeleteCustomerByIdUseCase;
import br.com.hexagonal.customer_api.application.core.usecase.FindCustomerByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteCustomerByIdConfig {

    @Bean
    public DeleteCustomerByIdUseCase deleteCustomerByIdUseCase(
            FindCustomerByIdUseCase findCustomerByIdUseCase,
            DeleteCustomerByIdAdapter deleteCustomerByIdAdapter
    ){
        return new DeleteCustomerByIdUseCase(findCustomerByIdUseCase, deleteCustomerByIdAdapter);
    }
}
