package cn.iocoder.yudao.module.hrsys.service.client;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.hrsys.controller.admin.client.vo.*;
import cn.iocoder.yudao.module.hrsys.dal.dataobject.client.ClientDO;
import cn.iocoder.yudao.module.hrsys.dal.mysql.client.ClientMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.hrsys.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
* {@link ClientServiceImpl} 的单元测试类
*
* @author 芋道源码
*/
@Import(ClientServiceImpl.class)
public class ClientServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ClientServiceImpl clientService;

    @Resource
    private ClientMapper clientMapper;

    @Test
    public void testCreateClient_success() {
        // 准备参数
        ClientCreateReqVO reqVO = randomPojo(ClientCreateReqVO.class);

        // 调用
        Long clientId = clientService.createClient(reqVO);
        // 断言
        assertNotNull(clientId);
        // 校验记录的属性是否正确
        ClientDO client = clientMapper.selectById(clientId);
        assertPojoEquals(reqVO, client);
    }

    @Test
    public void testUpdateClient_success() {
        // mock 数据
        ClientDO dbClient = randomPojo(ClientDO.class);
        clientMapper.insert(dbClient);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ClientUpdateReqVO reqVO = randomPojo(ClientUpdateReqVO.class, o -> {
            o.setId(dbClient.getId()); // 设置更新的 ID
        });

        // 调用
        clientService.updateClient(reqVO);
        // 校验是否更新正确
        ClientDO client = clientMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, client);
    }

    @Test
    public void testUpdateClient_notExists() {
        // 准备参数
        ClientUpdateReqVO reqVO = randomPojo(ClientUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> clientService.updateClient(reqVO), CLIENT_NOT_EXISTS);
    }

    @Test
    public void testDeleteClient_success() {
        // mock 数据
        ClientDO dbClient = randomPojo(ClientDO.class);
        clientMapper.insert(dbClient);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbClient.getId();

        // 调用
        clientService.deleteClient(id);
       // 校验数据不存在了
       assertNull(clientMapper.selectById(id));
    }

    @Test
    public void testDeleteClient_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> clientService.deleteClient(id), CLIENT_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetClientPage() {
       // mock 数据
       ClientDO dbClient = randomPojo(ClientDO.class, o -> { // 等会查询到
           o.setClientType(null);
           o.setClientName(null);
           o.setClientAddress(null);
           o.setContact(null);
           o.setContactPhone(null);
           o.setRemark(null);
           o.setGroupId(null);
           o.setCreateTime(null);
           o.setStatus(null);
       });
       clientMapper.insert(dbClient);
       // 测试 clientType 不匹配
       clientMapper.insert(cloneIgnoreId(dbClient, o -> o.setClientType(null)));
       // 测试 clientName 不匹配
       clientMapper.insert(cloneIgnoreId(dbClient, o -> o.setClientName(null)));
       // 测试 clientAddress 不匹配
       clientMapper.insert(cloneIgnoreId(dbClient, o -> o.setClientAddress(null)));
       // 测试 contact 不匹配
       clientMapper.insert(cloneIgnoreId(dbClient, o -> o.setContact(null)));
       // 测试 contactPhone 不匹配
       clientMapper.insert(cloneIgnoreId(dbClient, o -> o.setContactPhone(null)));
       // 测试 remark 不匹配
       clientMapper.insert(cloneIgnoreId(dbClient, o -> o.setRemark(null)));
       // 测试 groupId 不匹配
       clientMapper.insert(cloneIgnoreId(dbClient, o -> o.setGroupId(null)));
       // 测试 createTime 不匹配
       clientMapper.insert(cloneIgnoreId(dbClient, o -> o.setCreateTime(null)));
       // 测试 status 不匹配
       clientMapper.insert(cloneIgnoreId(dbClient, o -> o.setStatus(null)));
       // 准备参数
       ClientPageReqVO reqVO = new ClientPageReqVO();
       reqVO.setClientType(null);
       reqVO.setClientName(null);
       reqVO.setClientAddress(null);
       reqVO.setContact(null);
       reqVO.setContactPhone(null);
       reqVO.setRemark(null);
       reqVO.setGroupId(null);
       reqVO.setCreateTime((new Date[]{}));
       reqVO.setStatus(null);

       // 调用
       PageResult<ClientDO> pageResult = clientService.getClientPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbClient, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetClientList() {
       // mock 数据
       ClientDO dbClient = randomPojo(ClientDO.class, o -> { // 等会查询到
           o.setClientType(null);
           o.setClientName(null);
           o.setClientAddress(null);
           o.setContact(null);
           o.setContactPhone(null);
           o.setRemark(null);
           o.setGroupId(null);
           o.setCreateTime(null);
           o.setStatus(null);
       });
       clientMapper.insert(dbClient);
       // 测试 clientType 不匹配
       clientMapper.insert(cloneIgnoreId(dbClient, o -> o.setClientType(null)));
       // 测试 clientName 不匹配
       clientMapper.insert(cloneIgnoreId(dbClient, o -> o.setClientName(null)));
       // 测试 clientAddress 不匹配
       clientMapper.insert(cloneIgnoreId(dbClient, o -> o.setClientAddress(null)));
       // 测试 contact 不匹配
       clientMapper.insert(cloneIgnoreId(dbClient, o -> o.setContact(null)));
       // 测试 contactPhone 不匹配
       clientMapper.insert(cloneIgnoreId(dbClient, o -> o.setContactPhone(null)));
       // 测试 remark 不匹配
       clientMapper.insert(cloneIgnoreId(dbClient, o -> o.setRemark(null)));
       // 测试 groupId 不匹配
       clientMapper.insert(cloneIgnoreId(dbClient, o -> o.setGroupId(null)));
       // 测试 createTime 不匹配
       clientMapper.insert(cloneIgnoreId(dbClient, o -> o.setCreateTime(null)));
       // 测试 status 不匹配
       clientMapper.insert(cloneIgnoreId(dbClient, o -> o.setStatus(null)));
       // 准备参数
       ClientExportReqVO reqVO = new ClientExportReqVO();
       reqVO.setClientType(null);
       reqVO.setClientName(null);
       reqVO.setClientAddress(null);
       reqVO.setContact(null);
       reqVO.setContactPhone(null);
       reqVO.setRemark(null);
       reqVO.setGroupId(null);
       reqVO.setCreateTime((new Date[]{}));
       reqVO.setStatus(null);

       // 调用
       List<ClientDO> list = clientService.getClientList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbClient, list.get(0));
    }

}
