package com.lhf.dajiuye.api.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class CatItems implements Serializable {

    private String name;
    private String imageSrc;
    private String openType;
    private String navigatorUrl;

}
