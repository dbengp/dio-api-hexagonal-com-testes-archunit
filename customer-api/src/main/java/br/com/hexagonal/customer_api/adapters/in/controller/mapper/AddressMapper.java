package br.com.hexagonal.customer_api.adapters.in.controller.mapper;

import br.com.hexagonal.customer_api.adapters.in.controller.response.AddressResponse;
import br.com.hexagonal.customer_api.application.core.domain.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressResponse toAddressResponse(Address address){
        return new AddressResponse(
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getPostal());
    }
}
