package io.github.heinzyang.yangtools.common.dto;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author dylanMa
 * @description TODO
 * @createTime 2023-04-10
 */
public class ResultDTO<T> {

    private boolean success;

    @Nullable
    private ResultCode resultCode;

    private T data;

    private String message;

    private ResultDTO(boolean success, ResultCode resultCode, String message, T data) {
        this.success = success;
        this.resultCode = resultCode;
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message == null ? Optional.ofNullable(resultCode).map(ResultCode::getMessage).orElse("") : message;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isNotSuccess() {
        return !success;
    }


    public ResultCode getResultCode() {
        return resultCode;
    }

    public boolean isResultCode(ResultCode val) {
        return Objects.equals(this.resultCode, val);
    }


    public <U> ResultDTO<U> andThen(ResultDTO<U> resultDTO) {
        return andThen(() -> resultDTO);
    }

    public <U> ResultDTO<U> andThen(Supplier<ResultDTO<U>> supplier) {
        if (isNotSuccess()) {
            return ResultDTO.fail(resultCode, message);
        }
        return supplier.get();
    }


    public static <U> ResultDTO<U> fail(String message) {
        return of(false, BaseResultCode.OPERATION_FAIL, message, null);
    }

    public static <U> ResultDTO<U> fail(ResultCode resultCode, String message) {
        return of(false, resultCode, message, null);
    }

    public static ResultDTO<Void> success() {
        return success(null);
    }

    public static ResultDTO<Void> success(ResultCode resultCode) {
        return success(resultCode, resultCode.getMessage(), null);
    }

    public static <U> ResultDTO<U> success(U data) {
        return success(null, data);
    }

    public static <U> ResultDTO<U> success(String message, U data) {
        return success(BaseResultCode.SUCCESS, message, data);
    }

    public static <U> ResultDTO<U> success(ResultCode resultCode, String message, U data) {
        return of(true, resultCode, message, data);
    }

    public static <U> ResultDTO<U> of(boolean success, ResultCode resultCode, String message, U data) {
        return new ResultDTO<>(success, resultCode, message, data);
    }


}
