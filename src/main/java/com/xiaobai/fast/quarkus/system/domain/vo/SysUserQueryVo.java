package com.xiaobai.fast.quarkus.system.domain.vo;

import com.xiaobai.fast.quarkus.core.request.BaseQueryVo;

import java.util.Date;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */

public class SysUserQueryVo extends BaseQueryVo {

    private String username;

    private Integer userStatus;

    public SysUserQueryVo(Integer pageNum, Integer pageSize, Integer dateType, Date startTime, Date endTime, String username, Integer userStatus) {
        super(pageNum, pageSize, dateType, startTime, endTime);
        this.username = username;
        this.userStatus = userStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
}
