
package com.protocol.translate.nested.convert;

public interface Converter<S, T> {
    T convert(S source);
}
