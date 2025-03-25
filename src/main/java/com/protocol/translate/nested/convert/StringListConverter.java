package com.protocol.translate.nested.convert;


import java.util.List;
import java.util.stream.Collectors;

public class StringListConverter implements Converter<List<?>, List<?>> {
    @Override
    public List<?> convert(List<?> source) {

        return source.stream()
                .collect(Collectors.toList());
    }
}
