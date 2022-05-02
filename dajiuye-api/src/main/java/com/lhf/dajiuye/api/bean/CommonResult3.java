package com.lhf.dajiuye.api.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
public class CommonResult3<T> implements Serializable {
        public Map message;
        public Meta meta;
}
