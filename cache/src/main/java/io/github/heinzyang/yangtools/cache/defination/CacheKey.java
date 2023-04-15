package io.github.heinzyang.yangtools.cache.defination;

import javax.annotation.Nonnull;
import java.time.Duration;

/**
 * @author dylanMa
 * @description 缓存Key的定义
 * @createTime 2023-04-13
 */
public interface CacheKey {
    /**
     * 获取Key的前缀
     */
    @Nonnull
    String getKeyPrefix();

    /**
     * 拼接Key
     */
    default String getCacheKey(String... bizKeys) {
        StringBuilder sb = new StringBuilder();
        sb.append(getKeyPrefix());
        sb.append("-");
        for (String bizKey : bizKeys) {
            sb.append(bizKey);
            sb.append("-");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * 获取过期时长
     */
    @Nonnull
    Duration getExpiredTime();

}
