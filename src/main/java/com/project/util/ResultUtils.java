package com.project.util;

import com.project.common.BaseResponse;
import com.project.common.ErrorCode;

/**
 * 返回工具类
 *
 * @author yupi
 */
public class ResultUtils {

    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(200, data, "ok");
    }

    /**
     * 成功
     *
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(T data, String message) {
        return new BaseResponse<>(200, data, message);
    }

    /**
     * 成功
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(String message) {
        return new BaseResponse<>(200, null, message);
    }

    /**
     * 失败
     *
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败
     *
     * @param code
     * @param message
     * @param description
     * @return
     */
    public static BaseResponse error(Integer code, String message, String description) {
        return new BaseResponse(code, null, message, description);
    }

    /**
     * 失败
     *
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode, String message, String description) {
        return new BaseResponse(errorCode.getCode(), null, message, description);
    }

    /**
     * 失败
     *
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode, String description) {
        return new BaseResponse(errorCode.getCode(), errorCode.getMessage(), description);
    }

    /**
     * 失败
     *
     * @param message
     * @return
     */
    public static BaseResponse error(String message) {
        return new BaseResponse(null, message, null);
    }
}
