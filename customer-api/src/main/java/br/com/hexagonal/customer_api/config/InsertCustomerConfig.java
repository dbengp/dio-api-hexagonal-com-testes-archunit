package br.com.hexagonal.customer_api.config;

import br.com.hexagonal.customer_api.adapters.out.FindAddressByPostalAdapter;
import br.com.hexagonal.customer_api.adapters.out.InsertCustomerAdapter;
import br.com.hexagonal.customer_api.adapters.out.QueueCpfForValidationAdapter;
import br.com.hexagonal.customer_api.application.core.usecase.InsertCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertCustomerConfig {

    @Bean
    public InsertCustomerUseCase insertCustomerUseCase(
            FindAddressByPostalAdapter findAddressByPostalAdapter,
            InsertCustomerAdapter insertCustomerAdapter,
            QueueCpfForValidationAdapter queueCpfForValidationAdapter
    ){
        return new InsertCustomerUseCase(findAddressByPostalAdapter, insertCustomerAdapter, queueCpfForValidationAdapter);
    }

}
