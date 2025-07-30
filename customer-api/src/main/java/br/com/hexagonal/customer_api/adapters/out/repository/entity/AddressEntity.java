package br.com.hexagonal.customer_api.adapters.out.repository.entity;

import lombok.Data;

@Data
public class AddressEntity {

    private String street;
    private String city;
    private String state;
    private String country;
    private String postal;
}
