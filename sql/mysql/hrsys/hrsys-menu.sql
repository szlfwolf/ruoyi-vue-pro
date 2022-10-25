INSERT INTO `system_menu` VALUES (1300, '人力云', '', 1, 60, 0, '/hrcloud', 'ep:checked', NULL, 0, b'1', b'1', '1', '2021-12-30 20:26:36', '1', '2022-07-20 13:12:35', b'0');
INSERT INTO `system_menu` VALUES (1301, '客户管理', '', 1, 10, 1300, 'manager', 'ep:collection-tag', NULL, 0, b'1', b'1', '1', '2021-12-30 20:28:30', '1', '2022-07-20 14:50:31', b'0');

-- 菜单 SQL
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
    '客户管理', '', 2, 0, 1301,
    'client', '', 'hrsys/client/index', 0
);

-- 按钮父菜单ID
-- 暂时只支持 MySQL。如果你是 Oracle、PostgreSQL、SQLServer 的话，需要手动修改 @parentId 的部分的代码
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
    '客户查询', 'hrsys:client:query', 3, 1, @parentId,
    '', '', '', 0
);
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
    '客户创建', 'hrsys:client:create', 3, 2, @parentId,
    '', '', '', 0
);
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
    '客户更新', 'hrsys:client:update', 3, 3, @parentId,
    '', '', '', 0
);
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
    '客户删除', 'hrsys:client:delete', 3, 4, @parentId,
    '', '', '', 0
);
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
    '客户导出', 'hrsys:client:export', 3, 5, @parentId,
    '', '', '', 0
);
