package com.protocol.translate.nested.convert;


import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class SetConverter implements Converter<Set<?>, Set<?>> {
    @Override
    public Set<?> convert(Set<?> source) {
        return (Set<?>) convertCollection(source, "stringToInteger");
    }

    private Collection<?> convertCollection(Collection<?> collection, String elementConverter) {
        Converter<Object, Object> converter = ConverterRegistry.getConverter(elementConverter);
        return collection.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
