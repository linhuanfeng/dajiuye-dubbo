package com.lhf.dajiuye.api.bean.swipper;

import com.lhf.dajiuye.api.bean.swipper.City;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class Province implements Serializable {
    Integer id;
    String provinceName;
    String provinceAdcode;
    List<City> children;
}
