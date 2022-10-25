package cn.iocoder.yudao.module.hrsys.dal.mysql.client;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.hrsys.dal.dataobject.client.ClientDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.hrsys.controller.admin.client.vo.*;

/**
 * 客户 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ClientMapper extends BaseMapperX<ClientDO> {

    default PageResult<ClientDO> selectPage(ClientPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ClientDO>()
                .eqIfPresent(ClientDO::getClientType, reqVO.getClientType())
                .likeIfPresent(ClientDO::getClientName, reqVO.getClientName())
                .likeIfPresent(ClientDO::getClientAddress, reqVO.getClientAddress())
                .likeIfPresent(ClientDO::getContact, reqVO.getContact())
                .likeIfPresent(ClientDO::getContactPhone, reqVO.getContactPhone())
                .likeIfPresent(ClientDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ClientDO::getGroupId, reqVO.getGroupId())
                .betweenIfPresent(ClientDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ClientDO::getStatus, reqVO.getStatus())
                .orderByDesc(ClientDO::getId));
    }

    default List<ClientDO> selectList(ClientExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ClientDO>()
                .eqIfPresent(ClientDO::getClientType, reqVO.getClientType())
                .likeIfPresent(ClientDO::getClientName, reqVO.getClientName())
                .likeIfPresent(ClientDO::getClientAddress, reqVO.getClientAddress())
                .likeIfPresent(ClientDO::getContact, reqVO.getContact())
                .likeIfPresent(ClientDO::getContactPhone, reqVO.getContactPhone())
                .likeIfPresent(ClientDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ClientDO::getGroupId, reqVO.getGroupId())
                .betweenIfPresent(ClientDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ClientDO::getStatus, reqVO.getStatus())
                .orderByDesc(ClientDO::getId));
    }

}
