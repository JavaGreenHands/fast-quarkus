package com.xiaobai.fast.quarkus.system.rest;

import com.xiaobai.fast.quarkus.core.response.R;
import com.xiaobai.fast.quarkus.core.util.DateUtils;
import com.xiaobai.fast.quarkus.system.domain.DictData;
import com.xiaobai.fast.quarkus.system.domain.DictType;
import com.xiaobai.fast.quarkus.system.domain.vo.DictTypeQueryVo;
import com.xiaobai.fast.quarkus.system.service.DictTypeService;
import io.quarkus.runtime.util.StringUtil;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponseSchema;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * 字典类型接口
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@Path("/dict/type")
@Tag(name = "字典类型配置API",description = "字典类型配置API")
public class DictTypeResource {

    @Inject
    DictTypeService dictTypeService;

    @PUT
    @Path("")
    @Operation(summary = "新增字典类型",description = "新增字典类型")
    public R addDictType(@Valid DictType dictType){
        dictTypeService.addDictType(dictType);
        return R.ok();
    }

    @POST
    @Path("")
    @Operation(summary = "修改字典类型",description = "修改字典类型")
    public R updateDictType(@Valid DictType dictType){
        dictTypeService.updateDictType(dictType);
        return R.ok();
    }

    @GET
    @Path("/{dictTypeId}")
    @Operation(summary = "获取字典类型详情",description = "获取字典类型详情")
    @APIResponseSchema(value = DictType.class,responseDescription = "字典类型详情",responseCode = "200")
    public DictType getById(@PathParam("dictTypeId")Long dictTypeId){
       return dictTypeService.getById(dictTypeId);
    }

    @GET
    @Path("/data/{dictType}")
    @Operation(summary = "获取字典类型详情",description = "获取字典类型详情")
    @APIResponseSchema(value = DictType.class,responseDescription = "字典类型详情",responseCode = "200")
    public List<DictData> getDictDataListByType(@PathParam("dictType")String dictType){
        return dictTypeService.getDictDataListByType(dictType);
    }

    @GET
    @Path("/list")
    @Operation(summary = "获取字典类型列表",description = "获取字典类型列表")
    @Parameters({
            @Parameter(name = "dateType", description = "查询日期类型 1-创建时间 2-更新时间"),
            @Parameter(name = "startTime", description = "查询开始时间"),
            @Parameter(name = "endTime", description = "查询结束时间")
    })
    @APIResponse(
            responseCode = "200",
            description = "系统用户列表",
            content = @Content(
                    schema = @Schema(implementation = DictType.class, type = SchemaType.ARRAY))
    )
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<DictType> list(@BeanParam DictTypeQueryVo queryVo,
                               @QueryParam("dateType") Integer dateType,
                               @QueryParam("startTime") String startTime,
                               @QueryParam("endTime") String endTime){
        Date startDate =null;
        Date endDate =null;

        if(!StringUtil.isNullOrEmpty(startTime) && !StringUtil.isNullOrEmpty(endTime)){
            startDate =  DateUtils.parseDateTime(startTime);
            endDate =  DateUtils.parseDateTime(endTime);
            queryVo.setStartTime(startDate);
            queryVo.setEndTime(endDate);
            queryVo.setDateType(dateType);
        }

        return dictTypeService.list(queryVo);
    }
    @DELETE
    @Path("/{ids}")
    @Operation(summary = "获取字典类型详情",description = "获取字典类型详情")
    @APIResponseSchema(value = DictType.class,responseDescription = "字典类型详情",responseCode = "200")
    public R deleteById(@PathParam("ids")String ids){
        List<Long> deleteIds = Arrays.stream(ids.split(",")).map(Long::valueOf).toList();
        dictTypeService.deleteByIds(deleteIds);
        return R.ok();
    }

}
