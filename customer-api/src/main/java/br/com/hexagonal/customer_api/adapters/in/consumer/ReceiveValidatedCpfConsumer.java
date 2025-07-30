package br.com.hexagonal.customer_api.adapters.in.consumer;


import br.com.hexagonal.customer_api.adapters.in.consumer.mapper.CustomerMessageMapper;
import br.com.hexagonal.customer_api.adapters.in.consumer.message.CustomerMessage;
import br.com.hexagonal.customer_api.application.core.domain.Customer;
import br.com.hexagonal.customer_api.application.ports.in.UpdateCustomerInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiveValidatedCpfConsumer {

    @Autowired
    private UpdateCustomerInputPort updateCustomerInputPort;

    @Autowired
    private CustomerMessageMapper customerMessageMapper;

    @KafkaListener(topics = "cpf-validated-topic", groupId = "${spring.kafka.consumer.group-id}")
    public void receive(CustomerMessage customerMessage){
        Customer customer = customerMessageMapper.toCustomer(customerMessage);
        updateCustomerInputPort.update(customer, customerMessage.getPostal());
    }
}
