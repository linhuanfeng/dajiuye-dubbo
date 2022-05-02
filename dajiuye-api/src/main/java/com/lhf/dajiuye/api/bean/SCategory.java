package com.lhf.dajiuye.api.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class SCategory implements Serializable {
    Integer id;
    String sName;
    String sDesc;

    @JsonProperty("mId")
    Integer mId;
}
