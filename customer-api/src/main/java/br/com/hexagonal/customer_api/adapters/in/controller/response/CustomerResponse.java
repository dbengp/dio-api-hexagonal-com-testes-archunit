package br.com.hexagonal.customer_api.adapters.in.controller.response;


public record CustomerResponse(
        String name,
        String cpf,
        AddressResponse address
) {
}
