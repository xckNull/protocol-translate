package com.protocol.translate.nested.map;



import com.protocol.translate.nested.bo.*;

import java.util.ArrayList;
import java.util.List;

public class MappingConfig {
    public List<FieldMapping> getMappings(Class<?> sourceClass, Class<?> targetClass) {
        List<ClassMapping> list = new ArrayList<>();
        // 这里硬编码示例配置，实际应从配置文件加载
        List<FieldMapping> fieldMappings = new ArrayList<>();
        fieldMappings.add(new FieldMapping("user.name", "username", null, null,null,null, new ArrayList<>()));
        fieldMappings.add(new FieldMapping("address.city", "location.cityName", "cityConverter", null,null,null, new ArrayList<>()));
        fieldMappings.add(new FieldMapping("createTime", "timestamp", "dateToLong", null,null,null, new ArrayList<>()));
        fieldMappings.add(new FieldMapping("numbers", "numberList", "listConverter", "stringToInteger",null,null, new ArrayList<>()));
        fieldMappings.add(new FieldMapping("objectMap", "stringMap", "mapConverter", null,"upperCase","objectToString", new ArrayList<>()));


        List<FieldMapping> child3 = new ArrayList<>();
        child3.add(new FieldMapping("type", "type", null, null,null,null, new ArrayList<>()));
        ClassMapping classMapping3 = new ClassMapping(House.class, Build.class, child3);
        List<ClassMapping> elements33 = new ArrayList<>();
        elements33.add(classMapping3);

        List<FieldMapping> child2 = new ArrayList<>();
        child2.add(new FieldMapping("houses", "builds", null, null,null,null, elements33));
        ClassMapping classMapping2 = new ClassMapping(Car.class, Truck.class, child2);

        List<ClassMapping> elements2 = new ArrayList<>();
        elements2.add(classMapping3);

        List<FieldMapping> child1 = new ArrayList<>();
        child1.add(new FieldMapping("brand", "brand", null, null,null,null, new ArrayList<>()));
        List<ClassMapping> elements1 = new ArrayList<>();
        ClassMapping classMapping1 = new ClassMapping(Car.class, Truck.class, child1);

        List<FieldMapping> child4 = new ArrayList<>();
        child4.add(new FieldMapping("kilo", "kilo", null, null,null,null, new ArrayList<>()));
        List<ClassMapping> elements4 = new ArrayList<>();
        ClassMapping classMapping4 = new ClassMapping(Car.class, Truck.class, child4);

        elements1.add(classMapping4);
        elements1.add(classMapping1);
        elements1.add(classMapping2);

        fieldMappings.add(new FieldMapping("cars", "trucks", null, null,null,null, elements1));

        ClassMapping cl = new ClassMapping(SourceDTO.class, TargetVO.class, fieldMappings);
        list.add(cl);
        return fieldMappings;
    }
}
