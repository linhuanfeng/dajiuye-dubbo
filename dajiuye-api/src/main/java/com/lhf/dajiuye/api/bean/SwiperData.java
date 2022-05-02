package com.lhf.dajiuye.api.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class SwiperData implements Serializable {

    private String imageSrc;
    private String openType;
    private int goodsId;
    private String navigatorUrl;

}
