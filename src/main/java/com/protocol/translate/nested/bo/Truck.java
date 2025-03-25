package com.protocol.translate.nested.bo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Truck {
    public String brand;
    private String kilo;
    private List<Build> builds;
}
