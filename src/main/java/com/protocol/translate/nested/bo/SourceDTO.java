
package com.protocol.translate.nested.bo;


import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class SourceDTO {
    private User user;
    private Address address;
    private Date createTime;
    private List<String> numbers;
    private Map<String, String> objectMap;
    private List<Car> cars;
}
