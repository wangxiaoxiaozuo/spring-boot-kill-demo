package com.common.data.entity;

import com.common.data.enums.ErrorResultEnum;
import lombok.Getter;

/**
 * 业务异常类
 *
 * @date 2019/8/14
 */
public class BizException extends RuntimeException {

    @Getter
    private String code;

    public BizException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BizException(String message) {
        super(message);
        this.code = ErrorResultEnum.BAD_BIZ_REQUEST.getErrorCode();
    }

}
