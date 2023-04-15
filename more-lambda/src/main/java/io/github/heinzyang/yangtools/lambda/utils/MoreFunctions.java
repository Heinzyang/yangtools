package io.github.heinzyang.yangtools.lambda.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author dylanMa
 * @description more-functions
 * @createTime 2023-04-09
 */
public class MoreFunctions {

    private static final String FAIL_SAFE_MARK = "[fail safe] ";

    private static final Logger logger = LoggerFactory.getLogger(MoreFunctions.class);

    /**
     * [fail safe]运行Supplier.
     * 建议使用懒加载方法{@link MoreFunctions#catching(java.util.function.Supplier, java.util.function.Supplier)}
     *
     * @param supplier     Supplier
     * @param defaultValue 默认值
     */
    public static <T> T catching(Supplier<T> supplier, T defaultValue) {
        return catching(supplier, (Supplier<T>) () -> defaultValue);
    }

    /**
     * [fail safe]运行Supplier
     *
     * @param supplier     Supplier
     * @param defaultValue 默认值懒加载方法
     */
    public static <T> T catching(Supplier<T> supplier, Supplier<T> defaultValue) {
        return catching(supplier, (Function<Throwable, T>) throwable -> defaultValue.get());
    }

    /**
     * [fail safe]运行Supplier
     *
     * @param supplier          Supplier
     * @param throwableSupplier 异常处理方法
     */
    public static <T> T catching(Supplier<T> supplier, Function<Throwable, T> throwableSupplier) {
        try {
            T result = supplier.get();
            return result;
        } catch (Throwable throwable) {
            logger.error(FAIL_SAFE_MARK, throwable);
            return throwableSupplier.apply(throwable);
        }
    }

    /**
     * 以不抛出受查异常的方式，运行程序。
     */
    public static <T, X extends RuntimeException> T throwing(Callable<T> callable) throws X {
        try {
            T result = callable.call();
            return result;
        } catch (Throwable throwable) {
            MoreThrowable.throwIfUnchecked(throwable);
            throw new RuntimeException(throwable);
        }
    }

}
