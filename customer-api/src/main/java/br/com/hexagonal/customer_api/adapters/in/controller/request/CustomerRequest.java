package br.com.hexagonal.customer_api.adapters.in.controller.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public record CustomerRequest(

    @NotBlank
    String name,

    @NotBlank
    @Pattern(
            regexp = "(^\\d{11}$)|(^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$)",
            message = "CPF inválido. Utilize o formato NNN.NNN.NNN-NN ou 11 dígitos numéricos."
    )
    String cpf,
    @NotBlank
    String postal
){

}
