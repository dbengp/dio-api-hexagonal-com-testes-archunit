package br.com.hexagonal.customer_api.adapters.out;

import br.com.hexagonal.customer_api.application.ports.out.QueueCpfForValidationOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class QueueCpfForValidationAdapter implements QueueCpfForValidationOutputPort {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void queue(String cpf) {
        kafkaTemplate.send("cpf-for-validation-topic",cpf);
    }
}
