package br.com.hexagonal.customer_api.adapters.out.repository.mapper;

import br.com.hexagonal.customer_api.adapters.out.repository.entity.AddressEntity;
import br.com.hexagonal.customer_api.application.core.domain.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressEntityMapper {

    public AddressEntity toAddressEntity(Address address){

        AddressEntity addressEntity =  new AddressEntity();
        addressEntity.setStreet(address.getStreet());
        addressEntity.setCity(address.getCity());
        addressEntity.setState(address.getState());
        addressEntity.setCountry(address.getCountry());
        addressEntity.setPostal(address.getPostal());
        return addressEntity;
    }

    public Address toAddress(AddressEntity addressEntity){

        Address address =  new Address();
        address.setStreet(addressEntity.getStreet());
        address.setCity(addressEntity.getCity());
        address.setState(addressEntity.getState());
        address.setCountry(addressEntity.getCountry());
        address.setPostal(addressEntity.getPostal());
        return address;
    }
}
