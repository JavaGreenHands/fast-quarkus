package com.xiaobai.fast.quarkus.core.validator;

import jakarta.validation.groups.Default;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
public interface ValidationGroups {

    interface Create extends Default {

    }

    interface Update extends  Default{

    }
}
