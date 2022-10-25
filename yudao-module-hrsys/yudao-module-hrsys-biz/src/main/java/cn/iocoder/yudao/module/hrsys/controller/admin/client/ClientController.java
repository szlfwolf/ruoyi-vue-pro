package cn.iocoder.yudao.module.hrsys.controller.admin.client;

import cn.iocoder.yudao.framework.tenant.core.context.TenantContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.*;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.hrsys.controller.admin.client.vo.*;
import cn.iocoder.yudao.module.hrsys.dal.dataobject.client.ClientDO;
import cn.iocoder.yudao.module.hrsys.convert.client.ClientConvert;
import cn.iocoder.yudao.module.hrsys.service.client.ClientService;

@Api(tags = "管理后台 - 客户")
@RestController
@RequestMapping("/hrsys/client")
@Validated
public class ClientController {

    @Resource
    private ClientService clientService;

    @PostMapping("/create")
    @ApiOperation("创建客户")
    @PreAuthorize("@ss.hasPermission('hrsys:client:create')")
    public CommonResult<Long> createClient(@Valid @RequestBody ClientCreateReqVO createReqVO) {
        createReqVO.setTenantId(TenantContextHolder.getTenantId());
        return success(clientService.createClient(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新客户")
    @PreAuthorize("@ss.hasPermission('hrsys:client:update')")
    public CommonResult<Boolean> updateClient(@Valid @RequestBody ClientUpdateReqVO updateReqVO) {
        clientService.updateClient(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除客户")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('hrsys:client:delete')")
    public CommonResult<Boolean> deleteClient(@RequestParam("id") Long id) {
        clientService.deleteClient(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得客户")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('hrsys:client:query')")
    public CommonResult<ClientRespVO> getClient(@RequestParam("id") Long id) {
        ClientDO client = clientService.getClient(id);
        return success(ClientConvert.INSTANCE.convert(client));
    }

    @GetMapping("/list")
    @ApiOperation("获得客户列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('hrsys:client:query')")
    public CommonResult<List<ClientRespVO>> getClientList(@RequestParam("ids") Collection<Long> ids) {
        List<ClientDO> list = clientService.getClientList(ids);
        return success(ClientConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得客户分页")
    @PreAuthorize("@ss.hasPermission('hrsys:client:query')")
    public CommonResult<PageResult<ClientRespVO>> getClientPage(@Valid ClientPageReqVO pageVO) {
        PageResult<ClientDO> pageResult = clientService.getClientPage(pageVO);
        return success(ClientConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出客户 Excel")
    @PreAuthorize("@ss.hasPermission('hrsys:client:export')")
    @OperateLog(type = EXPORT)
    public void exportClientExcel(@Valid ClientExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ClientDO> list = clientService.getClientList(exportReqVO);
        // 导出 Excel
        List<ClientExcelVO> datas = ClientConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "客户.xls", "数据", ClientExcelVO.class, datas);
    }

}
