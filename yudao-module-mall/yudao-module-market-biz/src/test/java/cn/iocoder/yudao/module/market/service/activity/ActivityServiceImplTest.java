package cn.iocoder.yudao.module.market.service.activity;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.iocoder.yudao.module.market.enums.activity.MarketActivityStatusEnum;
import cn.iocoder.yudao.module.market.enums.activity.MarketActivityTypeEnum;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.market.controller.admin.activity.vo.*;
import cn.iocoder.yudao.module.market.dal.dataobject.activity.ActivityDO;
import cn.iocoder.yudao.module.market.dal.mysql.activity.ActivityMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.springframework.context.annotation.Import;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static cn.iocoder.yudao.module.market.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link ActivityServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(ActivityServiceImpl.class)
public class ActivityServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ActivityServiceImpl activityService;

    @Resource
    private ActivityMapper activityMapper;

    @Test
    public void testCreateActivity_success() {
        // 准备参数
        ActivityCreateReqVO reqVO = randomPojo(ActivityCreateReqVO.class,
                a -> a.setActivityType(MarketActivityTypeEnum.FULL_PRIVILEGE.getValue()),
                b -> b.setStatus(MarketActivityStatusEnum.WAIT.getValue()));

        // 调用
        Long activityId = activityService.createActivity(reqVO);
        // 断言
        assertNotNull(activityId);
        // 校验记录的属性是否正确
        ActivityDO activity = activityMapper.selectById(activityId);
        assertPojoEquals(reqVO, activity);
    }

    @Test
    public void testUpdateActivity_success() {
        // mock 数据
        ActivityDO dbActivity = randomPojo(ActivityDO.class);
        activityMapper.insert(dbActivity);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ActivityUpdateReqVO reqVO = randomPojo(ActivityUpdateReqVO.class, o -> {
            o.setId(dbActivity.getId()); // 设置更新的 ID
            o.setActivityType(MarketActivityTypeEnum.FULL_PRIVILEGE.getValue());
            o.setStatus(MarketActivityStatusEnum.WAIT.getValue());
        });

        // 调用
        activityService.updateActivity(reqVO);
        // 校验是否更新正确
        ActivityDO activity = activityMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, activity);
    }

    @Test
    public void testUpdateActivity_notExists() {
        // 准备参数
        ActivityUpdateReqVO reqVO = randomPojo(ActivityUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> activityService.updateActivity(reqVO), ACTIVITY_NOT_EXISTS);
    }

    @Test
    public void testDeleteActivity_success() {
        // mock 数据
        ActivityDO dbActivity = randomPojo(ActivityDO.class);
        activityMapper.insert(dbActivity);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbActivity.getId();

        // 调用
        activityService.deleteActivity(id);
        // 校验数据不存在了
        assertNull(activityMapper.selectById(id));
    }

    @Test
    public void testDeleteActivity_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> activityService.deleteActivity(id), ACTIVITY_NOT_EXISTS);
    }

    @Test
    public void testGetActivityPage() {
        // mock 数据
        ActivityDO dbActivity = randomPojo(ActivityDO.class);
        activityMapper.insert(dbActivity);
        insertCloneActivity(dbActivity);
        // 准备参数
        ActivityPageReqVO reqVO = new ActivityPageReqVO();
        reqVO.setTitle(dbActivity.getTitle());
        reqVO.setActivityType(dbActivity.getActivityType());
        reqVO.setStatus(dbActivity.getStatus());
        reqVO.setTimeLimitedDiscount(dbActivity.getTimeLimitedDiscount());
        reqVO.setFullPrivilege(dbActivity.getFullPrivilege());
        reqVO.setCreateTime(new Date[]{dbActivity.getCreateTime()});

        // 调用
        PageResult<ActivityDO> pageResult = activityService.getActivityPage(reqVO);
        // 断言
        assertEquals(1, pageResult.getTotal());
        assertEquals(1, pageResult.getList().size());
        assertPojoEquals(dbActivity, pageResult.getList().get(0));
    }

    @Test
    public void testGetActivityList() {
        // mock 数据
        ActivityDO dbActivity = randomPojo(ActivityDO.class);
        activityMapper.insert(dbActivity);
        insertCloneActivity(dbActivity);
        // 调用
        List<ActivityDO> list = activityService.getActivityList(Arrays.asList(dbActivity.getId()));
        // 断言
        assertPojoEquals(dbActivity, list.get(0));
    }


    private void insertCloneActivity(ActivityDO dbActivity) {
        // 测试 title 不匹配
        activityMapper.insert(cloneIgnoreId(dbActivity, o -> o.setTitle(o.getTitle() + System.currentTimeMillis())));
        // 测试 activityType 不匹配
        activityMapper.insert(cloneIgnoreId(dbActivity, o -> o.setActivityType(MarketActivityTypeEnum.FULL_PRIVILEGE.getValue())));
        // 测试 status 不匹配
        activityMapper.insert(cloneIgnoreId(dbActivity, o -> o.setStatus(MarketActivityStatusEnum.WAIT.getValue())));
        // 测试 timeLimitedDiscount 不匹配
        activityMapper.insert(cloneIgnoreId(dbActivity, o -> o.setTimeLimitedDiscount(o.getTimeLimitedDiscount() + System.currentTimeMillis())));
        // 测试 fullPrivilege 不匹配
        activityMapper.insert(cloneIgnoreId(dbActivity, o -> o.setFullPrivilege(o.getFullPrivilege() + System.currentTimeMillis())));

//        // 测试 startTime 不匹配
//        activityMapper.insert(cloneIgnoreId(dbActivity, o -> o.setStartTime(DateTime.now())));
//        // 测试 endTime 不匹配
//        activityMapper.insert(cloneIgnoreId(dbActivity, o -> o.setEndTime(DateTime.now())));
//        // 测试 deleteTime 不匹配
//        activityMapper.insert(cloneIgnoreId(dbActivity, o -> o.setDeleteTime(DateTime.now())));
//        // 测试 invalidTime 不匹配
//        activityMapper.insert(cloneIgnoreId(dbActivity, o -> o.setInvalidTime(DateTime.now())));
//        // 测试 createTime 不匹配
//        activityMapper.insert(cloneIgnoreId(dbActivity, o -> o.setCreateTime(DateTime.now())));

    }
}
