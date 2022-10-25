export type ClientVO = {
    id: number
    clientType: string
    clientName: string
    clientAddress: string
    contact: string
    contactPhone: string
    remark: string
    groupId: number
    status: number
}

export type ClientPageReqVO = {
    clientType: string
    clientName: string
    clientAddress: string
    contact: string
    contactPhone: string
    remark: string
    groupId: number
    createTime: date
    status: number
}

export type ClientExcelReqVO = {
    clientType: string
    clientName: string
    clientAddress: string
    contact: string
    contactPhone: string
    remark: string
    groupId: number
    createTime: date
    status: number
}