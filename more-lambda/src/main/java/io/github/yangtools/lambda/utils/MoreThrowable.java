package io.github.yangtools.lambda.utils;

import java.util.Objects;

/**
 * @author dylanMa
 * @description 更多异常处理类
 * @createTime 2023-04-09
 */
public class MoreThrowable {
    /**
     * 当异常为非受查异常时抛出。
     * @param throwable
     */
    public static void throwIfUnchecked(Throwable throwable){
        Objects.requireNonNull(throwable);
        if (throwable instanceof RuntimeException) {
            throw (RuntimeException) throwable;
        }
        if (throwable instanceof Error) {
            throw (Error) throwable;
        }
    }
}
