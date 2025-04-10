package com.volvo.emspmicroservice.common.dto;

import lombok.Data;
import java.util.List;

@Data
public class PageDTO<T> {

    // Total data number
    private Long totalNum;
    // Total page number
    private Long totalPage;
    // data list
    private List<T> list;

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotal_num(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
