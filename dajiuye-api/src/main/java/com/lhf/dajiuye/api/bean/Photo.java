package com.lhf.dajiuye.api.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Deprecated
public class Photo implements Serializable {
    String url;
    String name;
}
