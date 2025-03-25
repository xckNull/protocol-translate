
package com.protocol.translate.nested.bo;


import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class TargetVO {
    private String username;
    private Location location;
    private long timestamp;
    private List<String> numberList;
    private Map<String, String> stringMap;
    private List<Truck> trucks;
}
