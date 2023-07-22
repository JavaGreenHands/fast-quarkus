package com.xiaobai.fast.quarkus.controller;

import com.xiaobai.fast.quarkus.config.ApplicationConfig;
import com.xiaobai.fast.quarkus.core.ienum.ServiceCodeEnum;
import com.xiaobai.fast.quarkus.core.response.R;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/7/18
 * @since 1.0
 */
@Path("/common")
public class FileResource {
    @Inject
    private ApplicationConfig applicationConfig;

    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/upload")
    @POST
    @Consumes(value = MediaType.MULTIPART_FORM_DATA)
    public R<String> uploadFile(@MultipartForm FileUploadForm form){
        String fileName = form.getFileName();
        // TODO 校验fileName
        try (InputStream fileStream = form.getFile()) {
            // 指定上传的文件路径
            String uploadDir = applicationConfig.getUploadPath();
            Files.copy(fileStream, Paths.get(uploadDir, form.getFileName()));
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(ServiceCodeEnum.UPLOAD_ERROR.getCode(),ServiceCodeEnum.UPLOAD_ERROR.getMessage());
        }
        return R.success();
    }


}
