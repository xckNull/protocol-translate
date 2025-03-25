package com.protocol.translate.nested.convert;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatConverter implements Converter<Date, String> {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public String convert(Date source) {
        return sdf.format(source);
    }
}
