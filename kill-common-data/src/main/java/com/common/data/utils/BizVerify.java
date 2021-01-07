package com.common.data.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.common.data.entity.BizException;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * 业务校验器
 *
 * @author wangjian
 * @date 2020/4/17 13:03
 */
@UtilityClass
public final class BizVerify {

    public void verify(boolean expression, String errorMessageTemplate,
        Object... errorMessageArgs) {
        if (!expression) {
            throw new BizException(StrUtil.format(errorMessageTemplate, errorMessageArgs));
        }
    }

    public void verify(boolean expression, Supplier<String> supplier) {
        if (!expression) {
            throw new BizException(supplier.get());
        }
    }

    public Supplier<BizException> throwSupplier(String errorMessageTemplate,
        Object... errorMessageArgs) {
        return () -> new BizException(StrUtil.format(errorMessageTemplate, errorMessageArgs));
    }

    public BizException newInstance(String errorMessageTemplate, Object... errorMessageArgs) {
        return new BizException(StrUtil.format(errorMessageTemplate, errorMessageArgs));
    }

    public void isNull(Object reference, String errorMessageTemplate, Object... errorMessageArgs) {
        verify(reference == null, errorMessageTemplate, errorMessageArgs);
    }

    public void notNull(Object reference, String errorMessageTemplate, Object... errorMessageArgs) {
        verify(reference != null, errorMessageTemplate, errorMessageArgs);
    }

    public void collNotEmpty(Collection reference, String errorMessageTemplate,
        Object... errorMessageArgs) {
        verify(CollUtil.isNotEmpty(reference), errorMessageTemplate, errorMessageArgs);
    }

    public void collIsEmpty(Collection reference, String errorMessageTemplate,
        Object... errorMessageArgs) {
        verify(CollUtil.isEmpty(reference), errorMessageTemplate, errorMessageArgs);
    }

    public void stringNotBlank(String reference, String errorMessageTemplate,
        Object... errorMessageArgs) {
        verify(StrUtil.isNotBlank(reference), errorMessageTemplate, errorMessageArgs);
    }

    public void stringIsBlank(String reference, String errorMessageTemplate,
        Object... errorMessageArgs) {
        verify(StrUtil.isBlank(reference), errorMessageTemplate, errorMessageArgs);
    }

}
