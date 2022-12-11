package com.lhf.dajiuye.api.bean.swipper;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@TableName("community_bank")
public class CommunityBank implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Date createTime;
    private Date updateTime;
    private String tag;
    private String title;
    private String content;
}
