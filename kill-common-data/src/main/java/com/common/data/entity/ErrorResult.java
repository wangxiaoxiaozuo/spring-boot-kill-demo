package com.common.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 错误响应体
 *
 * @date 2019/8/14
 */
@Data
@Accessors(chain = true)
public class ErrorResult {

    @JsonProperty("error_code")
    private String errorCode;

    @JsonProperty("error_message")
    private String errorMessage;

}
