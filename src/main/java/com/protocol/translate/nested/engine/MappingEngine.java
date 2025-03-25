
package com.protocol.translate.nested.engine;
import com.protocol.translate.nested.map.ClassPair;
import com.protocol.translate.nested.map.MappingConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MappingEngine {
    private final Map<ClassPair, Mapper> mapperCache = new ConcurrentHashMap<>();
    private final MappingConfig config = new MappingConfig();

    public <S, T> T map(S source, Class<T> targetClass) throws Exception {
        ClassPair pair = new ClassPair(source.getClass(), targetClass);
        Mapper mapper = mapperCache.computeIfAbsent(pair, this::createMapper);
        return targetClass.cast(mapper.map(source));
    }

    private Mapper createMapper(ClassPair pair) {
        return new ReflectionMapper(pair, config.getMappings(pair.sourceClass(), pair.targetClass()));
    }
}
