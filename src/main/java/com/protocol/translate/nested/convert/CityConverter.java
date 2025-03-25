
package com.protocol.translate.nested.convert;

public class CityConverter implements Converter<String, String> {
    @Override
    public String convert(String source) {
        return source.toUpperCase() + "_CITY";
    }
}
