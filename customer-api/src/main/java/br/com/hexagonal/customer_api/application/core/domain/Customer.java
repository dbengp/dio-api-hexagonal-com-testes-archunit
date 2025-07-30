package br.com.hexagonal.customer_api.application.core.domain;

public class Customer {

    private String id;
    private String name;
    private Address address;
    private String cpf;
    private Boolean isCpf;

    public Customer() {
        this.isCpf = false;
    }

    public Customer(String id, String name, Address address, String cpf, Boolean isCpf) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cpf = cpf;
        this.isCpf = isCpf;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Boolean getIsCpf() {
        return isCpf;
    }

    public void setIsCpf(Boolean isCpf) {
        this.isCpf = isCpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
