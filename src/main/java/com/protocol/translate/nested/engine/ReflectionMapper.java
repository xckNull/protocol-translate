package com.protocol.translate.nested.engine;


import com.protocol.translate.nested.convert.Converter;
import com.protocol.translate.nested.convert.ConverterRegistry;
import com.protocol.translate.nested.map.ClassMapping;
import com.protocol.translate.nested.map.ClassPair;
import com.protocol.translate.nested.map.FieldMapping;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ReflectionMapper implements Mapper {
    private final Class<?> sourceClass;
    private final Class<?> targetClass;
    private final List<FieldMapping> mappings;

    public ReflectionMapper(ClassPair pair, List<FieldMapping> mappings) {
        this.sourceClass = pair.sourceClass();
        this.targetClass = pair.targetClass();
        this.mappings = mappings;
    }

    @Override
    public Object map(Object source) throws Exception {
        return mapInternal(source, targetClass, new ClassMapping(sourceClass, targetClass, mappings));
    }

    private <S, T> T mapInternal(S source, Class<T> targetClass, ClassMapping mapping) {
        try {
            //最终结果
            T target = targetClass.getDeclaredConstructor().newInstance();
            for (FieldMapping fieldMapping : mapping.getFieldMappings()) {
                processFieldMapping(source, target, fieldMapping);
            }
            return target;
        } catch (Exception e) {
            throw new RuntimeException("Mapping failed", e);
        }
    }

    private void processFieldMapping(Object source, Object target, FieldMapping mapping) throws Exception  {
        Object value = getNestedValue(source, mapping.getSourcePath());
        //普通处理
        if (mapping.getElementMappings().isEmpty()) {
            if (mapping.getConverter() != null) {
                Converter converter = ConverterRegistry.getConverter(mapping.getConverter());
                value = converter.convert(value);
            }
            setNestedField(target, mapping.getTargetField(), value);
        } else {
            //嵌套处理
            handleComplexMapping(value, target, mapping);
        }
    }

    private void handleComplexMapping(Object sourceValue, Object target, FieldMapping mapping) throws Exception {
        if (sourceValue instanceof Collection) {
            //集合处理
            processCollectionMapping((Collection<?>) sourceValue, target, mapping);
        } else if (sourceValue instanceof Map) {
            //map处理
//            processMapMapping((Map<?, ?>) sourceValue, target, mapping);
        } else {
            //普通处理
            processFieldMapping(sourceValue, target, mapping);
        }
    }

    private void processCollectionMapping(Collection<?> sourceCollection, Object target, FieldMapping mapping) throws Exception {
        Collection<Object> convertedCollection = new ArrayList<>();
        //遍历集合元素
        for (Object element : sourceCollection) { //car
            //获取path下对象
            List<ClassMapping> list = mapping.getElementMappings();
            Object convertedElement = mapElement(element, list.get(0).getTargetClass(), mapping);
            convertedCollection.add(convertedElement);
        }
        //集合一次性处理
        setNestedField(target, mapping.getTargetField(), convertedCollection);
    }

//    private Collection<Object> createTargetCollection() throws Exception {
//        // 根据目标字段类型创建对应集合
//        Field targetField = getTargetField(mapping.getTargetField());
//        Class<?> fieldType = targetField.getType();
//
//        if (List.class.isAssignableFrom(fieldType)) {
//            return new ArrayList<>();
//        } else if (Set.class.isAssignableFrom(fieldType)) {
//            return new LinkedHashSet<>(); // 保持顺序
//        } else if (Queue.class.isAssignableFrom(fieldType)) {
//            return new LinkedList<>();
//        }
//        throw new IllegalArgumentException("Unsupported collection type: " + fieldType.getName());
//    }

    private Object mapElement(Object element, Class target, FieldMapping mapping) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (!mapping.getElementMappings().isEmpty()) {
//            ClassMapping classMapping = new ClassMapping();
            List<FieldMapping> list = new ArrayList<>();
            for(ClassMapping classMapping : mapping.getElementMappings()) {
                list.addAll(classMapping.getFieldMappings());
//                return mapInternal(element, target, classMapping);
            }
            return mapInternal(element, target, new ClassMapping(element.getClass(), target, list));
        }
        if (mapping.getConverter() != null) {
            Converter converter = ConverterRegistry.getConverter(mapping.getConverter());
            element = converter.convert(element);
        }
        return element;
    }

    private Object getNestedValue(Object obj, String path) throws Exception {
        String[] parts = path.split("\\.");

        for (int i = 0;i<parts.length;i++) {
            if (obj instanceof List && i == parts.length - 1) {
                obj = getListNestedValue(obj, parts[i]);
                continue;
            }
            if (obj instanceof List && i != parts.length - 1) {
                obj = getListNestedValue11(obj, parts[i]);
                continue;
            }
            Field field = obj.getClass().getDeclaredField(parts[i]);
            field.setAccessible(true);
            obj = field.get(obj);
        }
        return obj;
    }

    private Object getListNestedValue11(Object obj, String path) throws Exception {
//        List<Object> list = new ArrayList<>();
        if (obj instanceof List) {
            for(Object o : (List<?>) obj) {
                Field field = o.getClass().getDeclaredField(path);
                field.setAccessible(true);
                obj = field.get(o);
//                list.add(obj);
            }
        }
        return obj;
    }

    private Object getListNestedValue(Object obj, String path) throws Exception {
//        String[] parts = path.split("\\.");
        List<Object> list = new ArrayList<>();
        if (obj instanceof List) {
            for(Object o : (List<?>) obj) {
                Field field = o.getClass().getDeclaredField(path);
                field.setAccessible(true);
                obj = field.get(o);
                list.add(obj);
            }
        }
//        for (String part : parts) {
//            Field field = obj.getClass().getDeclaredField(part);
//            field.setAccessible(true);
//            obj = field.get(obj);
//        }
        return list;
    }

    private void setNestedField(Object target, String path, Object value) throws Exception {
        String[] parts = path.split("\\.");
        Object current = target;
        int index = 0;
        for (int i = 0; i < parts.length - 1; i++) {
            index = i;
                    Field field = current.getClass().getDeclaredField(parts[i]);
            field.setAccessible(true);

            Object next = field.get(current);
            if (next == null) {
                next = field.getType().getDeclaredConstructor().newInstance();
            field.set(current, next);
        }
        current = next;
    }
        if (current instanceof List) {
        List<Object> tmpList = new ArrayList<>();
        if (value instanceof List) {
                for (Object o : (List<?>) value) {
                    Object obj = Class.forName("cn.telecom.wx.distribution.ota.platform.api.nested.bo.Truck").getDeclaredConstructor().newInstance();
//                    Field tmpField = ReflectionUtils.findField(obj.getClass(), parts[parts.length-1]);
                    Field tmpField = obj.getClass().getDeclaredField(parts[parts.length-1]);
                    tmpField.set(obj, o);
                    tmpList.add(obj);
                }
            }
            Field field = target.getClass().getDeclaredField(parts[index]);
            field.setAccessible(true);
//            field.set(current,tmpList);
            ReflectionUtils.setField(field, target, tmpList);
        } else {
            Field finalField = current.getClass().getDeclaredField(parts[parts.length-1]);
            finalField.setAccessible(true);
            finalField.set(current, value);
        }
    }
}
