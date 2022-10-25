package cn.iocoder.yudao.module.hrsys.dal.dataobject.client;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户 DO
 *
 * @author 芋道源码
 */
@TableName("hrsys_client")
@KeySequence("hrsys_client_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 客户类型
     *
     * 枚举 {@link cn.iocoder.yudao.module.hrsys.enums.client.HrsysClientTypeEnum}
     */
    private Integer clientType;
    /**
     * 客户名称
     */
    private String clientName;
    /**
     * 客户地址
     */
    private String clientAddress;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 联系人电话
     */
    private String contactPhone;
    /**
     * 备注
     */
    private String remark;
    /**
     * 小组ID
     */
    private Long groupId;
    /**
     * 状态
     *
     * 枚举 {@link cn.iocoder.yudao.framework.common.enums.CommonStatusEnum}
     */
    private Integer status;
    /**
     * 租户id
     */
    private Long tenantId;

}
