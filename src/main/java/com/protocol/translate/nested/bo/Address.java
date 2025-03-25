
package com.protocol.translate.nested.bo;


import lombok.Data;

@Data
public class Address {
    private String city;
    private String street;

    public Address(String newYork, String s) {
        this.city = newYork;
        this.street = s;
    }
}
