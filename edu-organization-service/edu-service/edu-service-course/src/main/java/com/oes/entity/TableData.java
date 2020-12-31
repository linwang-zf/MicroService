package com.oes.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author : JQK
 * @date : 2020-07-20 17:42
 * @description : 表格数据封装
 */
@Getter
@Setter
@AllArgsConstructor
public class TableData implements Serializable {

    private static final long serialVersionUID = 1773367504792742475L;
    /**
     * 总记录数
     */
    private long total;
    /**
     * 列表数据
     */
    private List<?> rows;

}
