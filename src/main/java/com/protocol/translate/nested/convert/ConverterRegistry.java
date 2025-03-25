
package com.protocol.translate.nested.convert;



import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConverterRegistry {
    private static final Map<String, Converter> converters = new ConcurrentHashMap<>();

    static {
        register("dateToLong", new DateToLongConverter());
        register("cityConverter", new CityConverter());
        register("listConverter", new ListConverter());
        register("stringToInteger", new StringToIntegerConverter());
        register("mapConverter", new MapConverter());
        register("upperCase", new UpperCaseConverter());
        register("objectToString", new ObjectToStringConverter());
        register("stringListConverter", new StringListConverter());
    }

    public static void register(String name, Converter converter) {
        converters.put(name, converter);
    }

    public static Converter getConverter(String name) {
        return converters.get(name);
    }
}
