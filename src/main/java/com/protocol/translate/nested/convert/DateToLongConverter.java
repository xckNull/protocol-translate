
package com.protocol.translate.nested.convert;


import java.util.Date;

public class DateToLongConverter implements Converter<Date, Long> {
    @Override
    public Long convert(Date source) {
        return source.getTime();
    }
}
