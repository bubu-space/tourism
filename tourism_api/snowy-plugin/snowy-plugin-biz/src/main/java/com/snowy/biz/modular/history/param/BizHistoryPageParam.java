package com.snowy.biz.modular.history.param;


import lombok.Data;

@Data
public class BizHistoryPageParam {

    private String spotId;

    private String title;

    private Integer pageNum;

    private Integer pageSize;
}
