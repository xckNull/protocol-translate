package com.protocol.translate.nested.convert;

public class UpperCaseConverter implements Converter<String, String> {
    @Override
    public String convert(String source) {
        return source.toUpperCase();
    }
}
