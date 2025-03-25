
package com.protocol.translate.nested.bo;


import lombok.Data;

@Data
public class User {
    private String name;
    private int age;

    public User(String john, int i) {
        this.name = john;
        this.age = i;
    }
}
