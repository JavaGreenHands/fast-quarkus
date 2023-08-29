package com.xiaobai.fast.quarkus.system.service;

import com.xiaobai.fast.quarkus.config.Constants;
import com.xiaobai.fast.quarkus.config.ienum.ServiceCodeEnum;
import com.xiaobai.fast.quarkus.core.exception.ServiceException;
import com.xiaobai.fast.quarkus.core.util.ExceptionUtils;
import com.xiaobai.fast.quarkus.system.domain.DictData;
import com.xiaobai.fast.quarkus.system.domain.DictType;
import com.xiaobai.fast.quarkus.system.domain.vo.DictDataQueryVo;
import com.xiaobai.fast.quarkus.system.repository.DictDataRepository;
import io.quarkus.cache.CacheInvalidate;
import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import io.quarkus.runtime.util.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典数据表-业务逻辑处理
 *
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@ApplicationScoped
public class DictDataService {

    @Inject
    DictDataRepository dictDataRepository;


    /**
     * 新增字典数据
     *
     * @param dictData dictData
     */
    @Transactional(rollbackOn = Exception.class)
    public void addDictData(DictData dictData) {
        dictDataRepository.persist(dictData);
    }

    /**
     * 修改字典数据
     *
     * @param dictData dictData
     */
    @Transactional(rollbackOn = Exception.class)
    public void updateDictData(DictData dictData) {
        DictData exits = dictDataRepository.find("isDel = 0 AND dictDataId = :dictDataId", dictData.getDictDataId()).singleResultOptional().orElseThrow(ExceptionUtils::getDataNotFoundException);
        if (exits == null) {
            throw new ServiceException(ServiceCodeEnum.DATA_NOT_FOUND, ServiceCodeEnum.DATA_NOT_FOUND);
        }
        exits.setDictTypeId(dictData.getDictTypeId());
        exits.setLabelName(dictData.getLabelName());
        exits.setLabelValue(dictData.getLabelValue());
        exits.setSortBy(dictData.getSortBy());
        exits.setStatus(dictData.getStatus());
    }

    /**
     * 查询字典详情
     *
     * @param dictDataId dictDataId
     * @return DictData
     */
    public DictData getById(Long dictDataId) {
       return dictDataRepository.find(" FROM DictData WHERE isDel = 0 AND dictDataId = ?1", dictDataId).singleResultOptional().orElseThrow(ExceptionUtils::getDataNotFoundException);
    }

    @Transactional(rollbackOn = Exception.class)
    public void deleteByIds(List<Long> deleteIds) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ids", deleteIds);
        dictDataRepository.update("isDel=1 AND dictDataId in (:ids)", paramMap);

    }

    /**
     * 查询列表数据
     *
     * @param queryVo queryVo
     * @return List<DictData>
     */
    public List<DictData> list(DictDataQueryVo queryVo) {
        StringBuilder hql = new StringBuilder();
        hql.append("FROM DictData where isDel = 0 ");
        Map<String, Object> parameterMap = new HashMap<>();
        if (!StringUtil.isNullOrEmpty(queryVo.getLabelName())) {
            hql.append("AND labelName like :labelName");
            parameterMap.put("labelName", queryVo.getLabelName());
        }
        if (!StringUtil.isNullOrEmpty(queryVo.getStatus())) {
            hql.append("AND status = :status");
            parameterMap.put("status", queryVo.getStatus());
        }
        if (queryVo.getDictTypeId() != null) {
            hql.append("AND dictTypeId = :dictTypeId");
            parameterMap.put("dictTypeId", queryVo.getDictTypeId());
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
        PanacheQuery<DictData> query = dictDataRepository.find(hql.toString(),
                Sort.by("createTime", Sort.Direction.Descending), parameterMap);
        return query.page(queryVo.getPageNum(), queryVo.getPageSize()).list();

    }
}
