package com.protocol.translate.nested.map;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class ClassMapping {
    private  Class<?> sourceClass;
    private  Class<?> targetClass;
    private  List<FieldMapping> fieldMappings;

}
