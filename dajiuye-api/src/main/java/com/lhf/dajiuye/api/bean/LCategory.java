package com.lhf.dajiuye.api.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class LCategory implements Serializable {
    Integer id;
    String lName;
    String lDesc;
    List<MCategory> children;
}
