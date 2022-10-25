package cn.iocoder.yudao.module.hrsys.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * market 错误码枚举类
 * <p>
 * 人力云 系统，使用 1-010-000-000 段
 */
public interface ErrorCodeConstants {
    // ========== 客户相关  1-010-000-xxx============
    ErrorCode CLIENT_NOT_EXISTS = new ErrorCode(1010000000, "客户不存在");
}
