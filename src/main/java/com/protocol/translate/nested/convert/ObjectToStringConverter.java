package com.protocol.translate.nested.convert;

public class ObjectToStringConverter implements Converter<Object, String> {
    @Override
    public String convert(Object source) {
        return source.toString();
    }
}
