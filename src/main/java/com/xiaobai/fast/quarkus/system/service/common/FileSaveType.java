package com.xiaobai.fast.quarkus.system.service.common;

/**
 * 文件保存类型
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
public enum FileSaveType {

    LOCAL("local"),
    QI_NIU("qiniu"),

    ;
    private final String name;

    public String getName() {
        return name;
    }

    FileSaveType(String name) {
        this.name = name;
    }
}
