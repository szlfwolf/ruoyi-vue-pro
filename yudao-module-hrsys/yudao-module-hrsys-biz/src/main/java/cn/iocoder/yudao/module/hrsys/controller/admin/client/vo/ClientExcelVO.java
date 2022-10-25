package cn.iocoder.yudao.module.hrsys.controller.admin.client.vo;

import cn.iocoder.yudao.module.hrsys.enums.DictTypeConstants;
import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;


/**
 * 客户 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class ClientExcelVO {

    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty(value = "客户类型", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.HRSYS_CLIENT_TYPE)
    private Integer clientType;

    @ExcelProperty("客户名称")
    private String clientName;

    @ExcelProperty("客户地址")
    private String clientAddress;

    @ExcelProperty("联系人")
    private String contact;

    @ExcelProperty("联系人电话")
    private String contactPhone;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("小组ID")
    private Long groupId;

    @ExcelProperty("创建时间")
    private Date createTime;

    @ExcelProperty("状态")
    @DictFormat(DictTypeConstants.COMMON_STATUS)
    private Integer status;

}
