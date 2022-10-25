package cn.iocoder.yudao.module.hrsys.controller.admin.client.vo;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.validation.InEnum;
import cn.iocoder.yudao.module.hrsys.enums.client.HrsysClientTypeEnum;
import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 客户 Excel 导出 Request VO", description = "参数和 ClientPageReqVO 是一致的")
@Data
public class ClientExportReqVO {

    @ApiModelProperty(value = "客户类型")
    @InEnum(HrsysClientTypeEnum.class)
    private Integer clientType;

    @ApiModelProperty(value = "客户名称")
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

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

    @ApiModelProperty(value = "状态（0正常 1停用）")
    @InEnum(CommonStatusEnum.class)
    private Integer status;

}
