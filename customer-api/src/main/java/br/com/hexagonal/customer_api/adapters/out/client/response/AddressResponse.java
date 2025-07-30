package br.com.hexagonal.customer_api.adapters.out.client.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressResponse {
    private String street;
    private String city;
    private String state;
    private String country;
    private String postal;
}
