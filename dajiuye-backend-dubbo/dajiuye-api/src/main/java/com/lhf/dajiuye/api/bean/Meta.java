package com.lhf.dajiuye.api.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
//@Component
public class Meta implements Serializable {

    private String msg;
    private int status;
}
