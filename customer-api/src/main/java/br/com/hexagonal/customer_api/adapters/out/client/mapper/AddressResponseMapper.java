package br.com.hexagonal.customer_api.adapters.out.client.mapper;

import br.com.hexagonal.customer_api.adapters.out.client.response.AddressResponse;
import br.com.hexagonal.customer_api.application.core.domain.Address;
import org.springframework.stereotype.Component;


@Component
public class AddressResponseMapper {

    public Address toAddress(AddressResponse addressResponse){
        return new Address(
                addressResponse.getStreet(),
                addressResponse.getCity(),
                addressResponse.getState(),
                addressResponse.getCountry(),
                addressResponse.getPostal()
        );
    }
}
