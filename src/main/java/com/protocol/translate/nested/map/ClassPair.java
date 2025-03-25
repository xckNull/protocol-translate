
package com.protocol.translate.nested.map;


import lombok.Data;

@Data
public class ClassPair {
    private final Class<?> sourceClass;
    private final Class<?> targetClass;

    public Class<?> sourceClass() {
        return sourceClass;
    }

    public Class<?> targetClass() {
        return targetClass;
    }
}
