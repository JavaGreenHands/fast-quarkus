package com.xiaobai.fast.quarkus.system.rest;

import com.xiaobai.fast.quarkus.core.response.R;
import com.xiaobai.fast.quarkus.core.util.DateUtils;
import com.xiaobai.fast.quarkus.system.domain.DictData;
import com.xiaobai.fast.quarkus.system.domain.vo.DictDataQueryVo;
import com.xiaobai.fast.quarkus.system.service.DictDataService;
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
 * 字典数据接口
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@Path("/dict/data")
@Tag(name = "字典数据配置API",description = "字典数据配置API")
public class DictDataResource {

    @Inject
    DictDataService dictDataService;

    @PUT
    @Path("")
    @Operation(summary = "新增字典数据",description = "新增字典数据")
    public R addDictData(@Valid DictData dictData){
        dictDataService.addDictData(dictData);
        return R.ok();
    }

    @POST
    @Path("")
    @Operation(summary = "修改字典数据",description = "修改字典数据")
    public R updateDictData(@Valid DictData dictData){
        dictDataService.updateDictData(dictData);
        return R.ok();
    }

    @GET
    @Path("/{dictDataId}")
    @Operation(summary = "获取字典数据详情",description = "获取字典数据详情")
    @APIResponseSchema(value = DictData.class,responseDescription = "字典数据详情",responseCode = "200")
    public DictData getById(@PathParam("dictDataId")Long dictDataId){
       return dictDataService.getById(dictDataId);
    }



    @GET
    @Path("/list")
    @Operation(summary = "获取字典数据列表",description = "获取字典数据列表")
    @Parameters({
            @Parameter(name = "dateType", description = "查询日期类型 1-创建时间 2-更新时间"),
            @Parameter(name = "startTime", description = "查询开始时间"),
            @Parameter(name = "endTime", description = "查询结束时间")
    })
    @APIResponse(
            responseCode = "200",
            description = "系统用户列表",
            content = @Content(
                    schema = @Schema(implementation = DictData.class, type = SchemaType.ARRAY))
    )
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<DictData> list(@BeanParam DictDataQueryVo queryVo,
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

        return dictDataService.list(queryVo);
    }
    @DELETE
    @Path("/{ids}")
    @Operation(summary = "获取字典数据详情",description = "获取字典数据详情")
    @APIResponseSchema(value = DictData.class,responseDescription = "字典数据详情",responseCode = "200")
    public R deleteById(@PathParam("ids")String ids){
        List<Long> deleteIds = Arrays.stream(ids.split(",")).map(Long::valueOf).toList();
        dictDataService.deleteByIds(deleteIds);
        return R.ok();
    }

}
