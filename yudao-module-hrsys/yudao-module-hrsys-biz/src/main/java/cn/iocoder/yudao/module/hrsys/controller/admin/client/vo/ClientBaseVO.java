package cn.iocoder.yudao.module.hrsys.controller.admin.client.vo;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.validation.InEnum;
import cn.iocoder.yudao.module.hrsys.enums.client.HrsysClientTypeEnum;
import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 客户 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ClientBaseVO {

    @ApiModelProperty(value = "客户类型（S:供应商，C:用工单位）")
    @InEnum(HrsysClientTypeEnum.class)
    private Integer clientType;

    @ApiModelProperty(value = "客户名称", required = true)
    @NotNull(message = "客户名称不能为空")
    private String clientName;

    @ApiModelProperty(value = "客户地址")
    private String clientAddress;

    @ApiModelProperty(value = "联系人")
    private String contact;

    @ApiModelProperty(value = "联系人电话")
    private String contactPhone;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "小组ID")
    private Long groupId;

    @ApiModelProperty(value = "状态", required = true)
    @NotNull(message = "状态不能为空")
    @InEnum(CommonStatusEnum.class)
    private Integer status;

    @ApiModelProperty(value = "租户id")
    private Long tenantId;

}
