package com.xiaobai.fast.quarkus.system.service;

import com.xiaobai.fast.quarkus.config.ienum.ServiceCodeEnum;
import com.xiaobai.fast.quarkus.core.exception.ServiceException;
import com.xiaobai.fast.quarkus.core.util.ExceptionUtils;
import com.xiaobai.fast.quarkus.system.domain.DictData;
import com.xiaobai.fast.quarkus.system.domain.DictType;
import com.xiaobai.fast.quarkus.system.domain.vo.DictTypeQueryVo;
import com.xiaobai.fast.quarkus.system.repository.DictDataRepository;
import com.xiaobai.fast.quarkus.system.repository.DictTypeRepository;
import com.xiaobai.fast.quarkus.utils.CollectionUtils;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import io.quarkus.runtime.util.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.*;

/**
 * 字典类型业务处理
 *
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@ApplicationScoped
public class DictTypeService {
    @Inject
    DictTypeRepository dictTypeRepository;

    @Inject
    DictDataRepository dictDataRepository;

    /**
     * 新增字典类型
     *
     * @param dictType dictType
     */
    @Transactional(rollbackOn = Exception.class)
    public void addDictType(DictType dictType) {
        DictType dictTypeInfo = dictTypeRepository.find("FROM DictType where isDel = 0 AND dictType = ?1", dictType.getDictType()).singleResultOptional().orElse(null);
		if(dictTypeInfo == null){
            dictTypeRepository.persist(dictType);
        }else {
            throw new ServiceException(ServiceCodeEnum.ERROR.getCode(),"dictType已存在,请重新输入");
        }
    }

    /**
     * 修改字典类型
     *
     * @param dictType dictType
     */
    @Transactional(rollbackOn = Exception.class)
    public void updateDictType(DictType dictType) {
        DictType exits = dictTypeRepository.find("FROM DictType where isDel = 0 AND dictTypeId = ?1", dictType.getDictTypeId()).singleResult();
        if (exits == null) {
            throw new ServiceException(ServiceCodeEnum.DATA_NOT_FOUND, ServiceCodeEnum.DATA_NOT_FOUND);
        }
        DictType dictTypeInfo = dictTypeRepository.find("FROM DictType where isDel = 0 AND dictType = ?1", dictType.getDictType()).singleResultOptional().orElse(null);
        if(dictTypeInfo!= null && !exits.getDictTypeId().equals( dictTypeInfo.getDictTypeId())){
            throw new ServiceException(ServiceCodeEnum.ERROR.getCode(),"dictType已存在,请重新输入");
        }
        exits.setDictType(dictType.getDictType());
        exits.setDictTypeName(dictType.getDictTypeName());
        exits.setStatus(dictType.getStatus());
        exits.setRemake(dictType.getRemake());
    }

    /**
     * 获取详情
     *
     * @param dictTypeId dictTypeId
     * @return DictType
     */
    public DictType getById(Long dictTypeId) {
        return dictTypeRepository.find("FROM DictType WHERE isDel = 0 AND dictTypeId = ?1", dictTypeId).singleResultOptional().orElseThrow(ExceptionUtils::getDataNotFoundException);

    }

    /**
     * 查询字典类型列表
     *
     * @param queryVo queryVo
     * @return List<DictType>
     */
    public List<DictType> list(DictTypeQueryVo queryVo) {
        StringBuilder hql = new StringBuilder();
        hql.append("FROM DictType where isDel = 0 ");
        Map<String, Object> parameterMap = new HashMap<>();
        if (!StringUtil.isNullOrEmpty(queryVo.getDictType())) {
            hql.append("AND dictType like :dictType");
            parameterMap.put("dictType", queryVo.getDictType());
        }
        if (!StringUtil.isNullOrEmpty(queryVo.getDictTypeName())) {
            hql.append("AND dictTypeName like :dictTypeName");
            parameterMap.put("dictTypeName", queryVo.getDictTypeName());
        }
        if (!StringUtil.isNullOrEmpty(queryVo.getStatus())) {
            hql.append("AND status = :status");
            parameterMap.put("status", queryVo.getStatus());
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
            parameterMap.put("startTime", queryVo.getStartTime());
            parameterMap.put("endTime", queryVo.getEndTime());
        }

        PanacheQuery<DictType> query = dictTypeRepository.find(hql.toString(),
                Sort.by("createTime", Sort.Direction.Descending), parameterMap);
        return query.page(queryVo.getPageNum(), queryVo.getPageSize()).list();
    }


    /**
     * 删除字典
     *
     * @param deleteIds deleteIds
     */
    @Transactional(rollbackOn = Exception.class)
    public void deleteByIds(List<Long> deleteIds) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ids", deleteIds);
        dictTypeRepository.update(" isDel=1 where dictTypeId in (:ids)", paramMap);
        dictDataRepository.update(" isDel=1 where dictTypeId in (:ids)", paramMap);

    }

    /**
     * 获取字典数据
     * @param dictType  dictType
     * @return dictType
     */
    public List<DictData> getDictDataListByType(String dictType) {
        DictType dictTypeInfo = dictTypeRepository.find("FROM DictType where isDel = 0 AND dictType = ?1", dictType).singleResultOptional().orElseThrow(ExceptionUtils::getDataNotFoundException);
        if(dictTypeInfo == null || CollectionUtils.isEmpty(dictTypeInfo.getDictDataList())){
            return new ArrayList<>();
        }
        return dictTypeInfo.getDictDataList().stream().filter(Objects::nonNull).filter(x -> x.getIsDel() == 0).toList();

    }
}
