import { useAxios } from '@/hooks/web/useAxios'
import { ClientVO,ClientPageReqVO,ClientExcelReqVO } from './types'

const request = useAxios()

// 查询客户列表
export const getClientPageApi = async (params: ClientPageReqVO) => {
    return await request.get({ url: '/hrsys/client/page', params })
}

// 查询客户详情
export const getClientApi = async (id: number) => {
    return await request.get({ url: '/hrsys/client/get?id=' + id })
}

// 新增客户
export const createClientApi = async (data: ClientVO) => {
    return await request.post({ url: '/hrsys/client/create', data })
}

// 修改客户
export const updateClientApi = async (data: ClientVO) => {
    return await request.put({ url: '/hrsys/client/update', data })
}

// 删除客户
export const deleteClientApi = async (id: number) => {
    return await request.delete({ url: '/hrsys/client/delete?id=' + id })
}

// 导出客户 Excel
export const exportClientApi = async (params: ClientExcelReqVO) => {
    return await request.download({ url: '/hrsys/client/export-excel', params })
}
