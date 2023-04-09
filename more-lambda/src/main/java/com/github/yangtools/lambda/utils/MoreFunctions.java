package com.github.yangtools.lambda.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
     * @param supplier Supplier
     * @param defaultValue 默认值
     */
    public static <T> T catching(Supplier<T> supplier, T defaultValue) {
        return catching(supplier, (Supplier<T>) () -> defaultValue);
    }

    /**
     * [fail safe]运行Supplier
     * @param supplier Supplier
     * @param defaultValue 默认值懒加载方法
     */
    public static <T> T catching(Supplier<T> supplier, Supplier<T> defaultValue) {
        try {
            T result = supplier.get();
            return result;
        } catch (Throwable throwable) {
            //todo
            logger.error(FAIL_SAFE_MARK, throwable);
            return defaultValue.get();
        }
    }


}
