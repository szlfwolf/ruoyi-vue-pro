import request from '@/utils/request'

// 创建客户
export function createClient(data) {
  return request({
    url: '/hrsys/client/create',
    method: 'post',
    data: data
  })
}

// 更新客户
export function updateClient(data) {
  return request({
    url: '/hrsys/client/update',
    method: 'put',
    data: data
  })
}

// 删除客户
export function deleteClient(id) {
  return request({
    url: '/hrsys/client/delete?id=' + id,
    method: 'delete'
  })
}

// 获得客户
export function getClient(id) {
  return request({
    url: '/hrsys/client/get?id=' + id,
    method: 'get'
  })
}

// 获得客户分页
export function getClientPage(query) {
  return request({
    url: '/hrsys/client/page',
    method: 'get',
    params: query
  })
}

// 导出客户 Excel
export function exportClientExcel(query) {
  return request({
    url: '/hrsys/client/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
