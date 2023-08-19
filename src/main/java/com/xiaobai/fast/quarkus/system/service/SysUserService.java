package com.xiaobai.fast.quarkus.system.service;

import com.xiaobai.fast.quarkus.config.ienum.ServiceCodeEnum;
import com.xiaobai.fast.quarkus.core.exception.ServiceException;
import com.xiaobai.fast.quarkus.system.domain.SysUser;
import com.xiaobai.fast.quarkus.system.domain.vo.SysUserQueryVo;
import com.xiaobai.fast.quarkus.system.repository.SysUserRepository;
import io.quarkus.panache.common.Sort;
import io.quarkus.runtime.util.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@ApplicationScoped
public class SysUserService {
    @Inject
    SysUserRepository sysUserRepository;
    /**
     * 添加一个用户
     * @param sysUser 用户
     */
    @Transactional
    public void addUser(SysUser sysUser) {
        sysUserRepository.persist(sysUser);
    }

    @Transactional
    public void updateById(SysUser sysUser) {
        SysUser exits = sysUserRepository.findById(sysUser.getUserId());
        if(exits == null){
            throw new ServiceException(ServiceCodeEnum.DATA_NOT_FOUND,ServiceCodeEnum.DATA_NOT_FOUND);
        }
        exits.setSysRoleSet(sysUser.getSysRoleSet());
        exits.setUserId(sysUser.getUserId());
        exits.setUsername(sysUser.getUsername());
        exits.setNickName(sysUser.getNickName());
        exits.setPhoneNumber(sysUser.getPhoneNumber());
        exits.setAvatar(sysUser.getAvatar());

    }

    @Transactional
    public void deleteByIds(List<Long> deleteIds) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ids", deleteIds);
        sysUserRepository.update(" isDel=1 where userId in (:ids)", paramMap);

    }

    public SysUser getById(Long userId) {
        return sysUserRepository.find("isDel = 0 AND userId = :userId",userId).singleResult();
    }

    /**
     * 查询用户列表
     * @param queryVo 查询参数
     * @return 用户列表
     */
    public List<SysUser> getUserList(SysUserQueryVo queryVo) {
        StringBuilder hql = new StringBuilder();
        hql.append("FROM SysUser where isDel = 0");
        Map<String, Object> paramMap = new HashMap<>();
        if (queryVo.getUserStatus() != null) {
            hql.append(" AND userStatus = :userStatus");
            paramMap.put("userStatus",queryVo.getUserStatus());
        }
        if (!StringUtil.isNullOrEmpty(queryVo.getUsername())) {
            hql.append(" AND (username like  :username or nick_name like :username)");
            paramMap.put("username",queryVo.getUsername());
        }
        if (queryVo.getDateType() != null
                && queryVo.getStartTime() != null
                && queryVo.getEndTime() != null) {
            if (queryVo.getDateType() == 1) {
                hql.append("AND createTime >= :startTime AND createTime <= :endTime");
            }
            if (queryVo.getDateType() == 2) {
                hql.append("AND updateTime >= :startTime AND updateTime <= :endTime");
            }
            paramMap.put("startTime", queryVo.getStartTime());
            paramMap.put("endTime", queryVo.getEndTime());
        }
        return sysUserRepository.find(hql.toString(), Sort.by("createTime", Sort.Direction.Descending), paramMap).page(queryVo.getPageNum(), queryVo.getPageSize()).list();

    }
}
