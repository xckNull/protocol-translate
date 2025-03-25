package com.protocol.translate.nested.convert;


import java.util.Map;
import java.util.stream.Collectors;

public class MapConverter implements Converter<Map<?, ?>, Map<?, ?>> {
    @Override
    public Map<?, ?> convert(Map<?, ?> source) {

        return convertMap(source, "upperCase", "objectToString");
    }
//    private Map<?, ?> convertMap(Map<?, ?> map, FieldMapping mapping) {
    private Map<?, ?> convertMap(Map<?, ?> map, String keyConverterStr, String valueConverterStr) {
        Converter<Object, Object> keyConverter = ConverterRegistry.getConverter(keyConverterStr);
        Converter<Object, Object> valueConverter = ConverterRegistry.getConverter(valueConverterStr);

        return map.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> keyConverter.convert(e.getKey()),
                        e -> valueConverter.convert(e.getValue())
                ));
    }
}
