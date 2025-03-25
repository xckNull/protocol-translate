package com.protocol.translate.nested.convert;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ListConverter implements Converter<List<?>, List<?>> {
    @Override
    public List<?> convert(List<?> source) {
//        List<Object> result = new ArrayList<>();
//        for (Object item : source) {
//            result.add(engine.map(item, Object.class));
//        }
        return (List<?>) convertCollection(source,"stringToInteger");
    }

    private Collection<?> convertCollection(Collection<?> collection, String elementConverter) {
        Converter<Object, Object> converter = ConverterRegistry.getConverter(elementConverter);
        return collection.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
