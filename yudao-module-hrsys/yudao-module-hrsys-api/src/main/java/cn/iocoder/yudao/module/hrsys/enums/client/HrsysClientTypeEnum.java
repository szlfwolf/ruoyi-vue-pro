package cn.iocoder.yudao.module.hrsys.enums.client;

import cn.iocoder.yudao.framework.common.core.IntArrayValuable;

import java.util.Arrays;

public enum HrsysClientTypeEnum implements IntArrayValuable {

    SUPPLIER(1, "供应商"),
    FACTORY(2, "用工单位"),
    ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(HrsysClientTypeEnum::getValue).toArray();

    /**
     * 类型值
     */
    private final Integer value;
    /**
     * 类型名
     */
    private final String name;

    HrsysClientTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public int[] array() {
        return ARRAYS;
    }
}
