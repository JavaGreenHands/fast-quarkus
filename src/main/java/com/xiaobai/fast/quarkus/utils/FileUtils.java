package com.xiaobai.fast.quarkus.utils;

import com.xiaobai.fast.quarkus.config.Constants;

/**
 * 文件工具类
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
public class FileUtils {
    private static final CharSequence[] SPECIAL_SUFFIX = {"tar.bz2", "tar.Z", "tar.gz", "tar.xz"};

    /**
     * 类Unix路径分隔符
     */
    public static final char UNIX_SEPARATOR = Constants.SLASH;
    /**
     * Windows路径分隔符
     */
    public static final char WINDOWS_SEPARATOR = Constants.BACKSLASH;
    /**
     * 获得文件后缀名，扩展名不带“.”
     * 该代码来自hutool.cn
     * @param fileName 文件名
     * @return 扩展名
     * @since 5.3.8
     */
    public static String getSuffix(String fileName) {
        if (fileName == null) {
            return null;
        }
        int index = fileName.lastIndexOf(Constants.DOT);
        if (index == -1) {
            return Constants.EMPTY;
        } else {
            // issue#I4W5FS@Gitee
            int secondToLastIndex = fileName.substring(0, index).lastIndexOf(Constants.DOT);
            String substr = fileName.substring(secondToLastIndex == -1 ? index : secondToLastIndex + 1);
            if (CommonUtils.containsAny(substr, SPECIAL_SUFFIX)) {
                return substr;
            }

            String ext = fileName.substring(index + 1);
            // 扩展名中不能包含路径相关的符号
            return CommonUtils.containsAny(ext, UNIX_SEPARATOR, WINDOWS_SEPARATOR) ? Constants.EMPTY : ext;

        }
    }


}