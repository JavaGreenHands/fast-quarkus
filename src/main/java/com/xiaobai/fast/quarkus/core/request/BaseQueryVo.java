package com.xiaobai.fast.quarkus.core.request;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.QueryParam;

import java.util.Date;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/7/18
 * @since 1.0
 */
public class BaseQueryVo {
    /**
     * 页码
     */
    @QueryParam("pageNum")
    @NotNull(message = "分页参数不能为空")
    protected Integer pageNum = 0;
    /**
     * 分页数量
     */
    @QueryParam("pageSize")
    @NotNull(message = "分页参数不能为空")
    protected Integer pageSize = 10;

    /**
     * 查询日期类型 1-创建时间 2-更新时间
     */
    protected Integer dateType;

    /**
     * 创建时间
     */
    protected Date startTime;

    /**
     * 更新时间
     */
    protected Date endTime;

    public BaseQueryVo(Integer pageNum, Integer pageSize, Integer dateType, Date startTime, Date endTime) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.dateType = dateType;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getDateType() {
        return dateType;
    }

    public void setDateType(Integer dateType) {
        this.dateType = dateType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BaseQueryVo(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public BaseQueryVo() {
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
