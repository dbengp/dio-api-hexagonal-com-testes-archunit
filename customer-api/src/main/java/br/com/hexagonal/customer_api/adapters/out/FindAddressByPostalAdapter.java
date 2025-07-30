package br.com.hexagonal.customer_api.adapters.out;

import br.com.hexagonal.customer_api.adapters.out.client.FindAddressByPostalClient;
import br.com.hexagonal.customer_api.adapters.out.client.mapper.AddressResponseMapper;
import br.com.hexagonal.customer_api.adapters.out.client.response.AddressResponse;
import br.com.hexagonal.customer_api.application.core.domain.Address;
import br.com.hexagonal.customer_api.application.ports.out.FindAddressByPostalOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindAddressByPostalAdapter implements FindAddressByPostalOutputPort {

    @Autowired
    private FindAddressByPostalClient findAddressByPostalClient;

    @Autowired
    private AddressResponseMapper addressResponseMapper;

    @Override
    public Address find(String postal) {
        AddressResponse addressResponse = findAddressByPostalClient.find(postal);
        return addressResponseMapper.toAddress(addressResponse);
    }
}
