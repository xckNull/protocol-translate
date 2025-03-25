package com.protocol.translate.nested;



import com.protocol.translate.nested.bo.*;
import com.protocol.translate.nested.engine.MappingEngine;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        SourceDTO source = new SourceDTO();
        source.setUser(new User("John", 30));
        source.setAddress(new Address("New York", "5th Ave"));
        source.setCreateTime(new Date());
        source.setNumbers(Arrays.asList("1","2"));
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        source.setObjectMap(map);
        List<Car> cars = new ArrayList<>();
        Car car = new Car();
        car.setBrand("Honda");
        car.setKilo("1000");
        cars.add(car);
        House house = new House();
        house.setType("1");
        List<House> houses = new ArrayList<>();
        houses.add(house);
        car.setHouses(houses);
        source.setCars(cars);

        MappingEngine engine = new MappingEngine();
        TargetVO target = engine.map(source, TargetVO.class);

        System.out.println("Username: " + target.getUsername());
        System.out.println("City: " + target.getLocation().getCityName());
        System.out.println("Timestamp: " + target.getTimestamp());
        System.out.println("numberList: " + target.getNumberList());
        System.out.println("stringMap: " + target.getStringMap());
        System.out.println("trucks: " + target.getTrucks());
    }
}
