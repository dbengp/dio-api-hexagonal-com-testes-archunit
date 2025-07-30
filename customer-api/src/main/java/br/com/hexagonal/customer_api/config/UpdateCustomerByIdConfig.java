package br.com.hexagonal.customer_api.config;

import br.com.hexagonal.customer_api.adapters.out.FindAddressByPostalAdapter;
import br.com.hexagonal.customer_api.adapters.out.UpdateCustomerAdapter;
import br.com.hexagonal.customer_api.application.core.usecase.FindCustomerByIdUseCase;
import br.com.hexagonal.customer_api.application.core.usecase.UpdateCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateCustomerByIdConfig {

    @Bean
    public UpdateCustomerUseCase updateCustomerUseCase(
            FindCustomerByIdUseCase findCustomerByIdUseCase,
            FindAddressByPostalAdapter findAddressByPostalAdapter,
            UpdateCustomerAdapter updateCustomerAdapter
    ){
        return new UpdateCustomerUseCase(findCustomerByIdUseCase, findAddressByPostalAdapter, updateCustomerAdapter);
    }

}
