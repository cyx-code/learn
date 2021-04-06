package cn.coding.boot.tm.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryPage implements Serializable {
    // 当前页码
    private Integer pageCode;
    // 每页显示的记录数
    private Integer pageSize;
}
