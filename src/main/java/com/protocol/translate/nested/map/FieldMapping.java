package com.protocol.translate.nested.map;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FieldMapping {
    private final String sourcePath;
    private final String targetField;
    private final String converter;
    private final String elementConverter;
    private final String keyConverter;
    private final String valueConverter;
    private final List<ClassMapping> elementMappings;
}
