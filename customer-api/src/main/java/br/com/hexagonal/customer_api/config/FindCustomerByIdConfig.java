package br.com.hexagonal.customer_api.config;

import br.com.hexagonal.customer_api.adapters.out.FindCustomerByIdAdapter;
import br.com.hexagonal.customer_api.application.core.usecase.FindCustomerByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindCustomerByIdConfig {

    @Bean
    public FindCustomerByIdUseCase findCustomerByIdUseCase(
            FindCustomerByIdAdapter findCustomerByIdAdapter
    ){
        return new FindCustomerByIdUseCase(findCustomerByIdAdapter);
    }

}
