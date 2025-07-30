package br.com.hexagonal.customer_api.application.core.domain;

public class Address {

    private String street;
    private String city;
    private String state;
    private String country;
    private String postal;

    public Address() {
    }

    public Address(String street, String city, String state, String country, String postal) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postal = postal;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }
}
