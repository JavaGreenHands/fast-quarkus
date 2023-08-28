package com.xiaobai.fast.quarkus.controller;


import com.xiaobai.fast.quarkus.config.ApplicationConfig;
import com.xiaobai.fast.quarkus.system.domain.FileInfo;
import com.xiaobai.fast.quarkus.system.domain.vo.FileUploadForm;
import com.xiaobai.fast.quarkus.system.service.common.FileUploadStrategy;
import com.xiaobai.fast.quarkus.utils.CollectionUtils;
import io.quarkus.arc.All;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponseSchema;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@Path("/common")
@Tag(name = "文件上传下载",description = "文件上传下载")
public class FileResource {
    private static final Logger logger = LoggerFactory.getLogger(FileResource.class);
    private final Map<String, FileUploadStrategy> uploadStrategyMap = new HashMap<>();
    @Inject
    ApplicationConfig applicationConfig;

    @Inject
    public void setUploadStrategy(@All List<FileUploadStrategy> fileUploadStrategyList) {
        if (!CollectionUtils.isEmpty(fileUploadStrategyList)) {
            fileUploadStrategyList.forEach(x -> {
                uploadStrategyMap.put(x.getSaveFileType(), x);
            });

        } else {
            logger.warn("upload.type 没有配置上传文件功能无法使用！");
        }
    }

    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/upload")
    @POST
    @Consumes(value = MediaType.MULTIPART_FORM_DATA)
//    @PermitAll
    @Operation(summary = "上传文件",description = "上传文件")
    @Parameters({
            @Parameter(name = "file",description = "文件二进制流",required = true,
                    content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM)),
            @Parameter(name = "fileName", description = "文件名称"),

    })
    @APIResponseSchema(value = FileInfo.class,responseDescription = "文件信息",responseCode = "200")
    public FileInfo uploadFile(@MultipartForm FileUploadForm form) {
        String uploadType = applicationConfig.getUploadType();
        FileUploadStrategy fileUploadStrategy = uploadStrategyMap.get(uploadType);
        return fileUploadStrategy.saveFile(form.getFile(), form.getFileName());
    }

    @GET
    @Path("/download/{filename}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @PermitAll
    public Response getFile(@PathParam("filename") String filename) {
        try {
            File file = new File(applicationConfig.getUploadPath() + File.separator + filename);
            if (file.exists()) {
                byte[] content = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
                return Response.ok(content).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
