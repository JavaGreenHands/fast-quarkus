package com.xiaobai.fast.quarkus.controller;


/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/7/18
 * @since 1.0
 */
//@Path("/common")
public class FileResource {
//    @Inject
//    private ApplicationConfig applicationConfig;
//
//    @Produces(value = MediaType.APPLICATION_JSON)
//    @Path("/upload")
//    @POST
//    @Consumes(value = MediaType.MULTIPART_FORM_DATA)
//    public Response uploadFile(@MultipartForm FileUploadForm form){
//        String fileName = form.getFileName();
//        // TODO 校验fileName
//        try (InputStream fileStream = form.getFile()) {
//            // 指定上传的文件路径
//            String uploadDir = applicationConfig.getUploadPath();
//            Files.copy(fileStream, Paths.get(uploadDir, form.getFileName()));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.status(500).entity();
//        }
//        return R.success();
//    }


}
