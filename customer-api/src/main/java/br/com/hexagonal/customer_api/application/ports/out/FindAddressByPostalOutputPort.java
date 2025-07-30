package br.com.hexagonal.customer_api.application.ports.out;

import br.com.hexagonal.customer_api.application.core.domain.Address;

public interface FindAddressByPostalOutputPort {

    Address find(String postal);
}
