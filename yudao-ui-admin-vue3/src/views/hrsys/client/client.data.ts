import { reactive } from 'vue'
import { useI18n } from '@/hooks/web/useI18n'
import { CrudSchema, useCrudSchemas } from '@/hooks/web/useCrudSchemas'
import { DICT_TYPE } from '@/utils/dict'
const { t } = useI18n() // 国际化
// 表单校验
export const rules = reactive({
    clientName: [{ required: true, message: "客户名称不能为空", trigger: "blur" }],
    status: [{ required: true, message: "状态（0正常 1停用）不能为空", trigger: "blur" }],
})
// CrudSchema
const crudSchemas = reactive<CrudSchema[]>([
    {
      label: '编号',
      field: 'id',
      type: 'index',
      form: {
         show: false
      },
      detail: {
         show: false
      }
    },
    {
      label: '客户类型（S:供应商，C:用工单位）',
      field: 'clientType',
      dictType: DICT_TYPE.HRSYS_CLIENT_TYPE,
      search: {
         show: true
      }
    },
    {
      label: '客户名称',
      field: 'clientName',
      form: {
          show: true,
      },
      search: {
         show: true
      }
    },
    {
      label: '客户地址',
      field: 'clientAddress',
      form: {
          show: true,
      },
      search: {
         show: true
      }
    },
    {
      label: '联系人',
      field: 'contact',
      form: {
          show: true,
      },
      search: {
         show: true
      }
    },
    {
      label: '联系人电话',
      field: 'contactPhone',
      form: {
          show: true,
      },
      search: {
         show: true
      }
    },
    {
      label: '备注',
      field: 'remark',
      form: {
          show: true,
      },
      search: {
         show: true
      }
    },
    {
      label: '小组ID',
      field: 'groupId',
      form: {
          show: true,
      },
      search: {
         show: true
      }
    },
    {
      label: '创建时间',
      field: 'createTime',
      form: {
         false
      },
      search: {
         show: true,
         component: 'DatePicker',
         componentProps: {
             type: 'datetimerange',
             valueFormat: 'YYYY-MM-DD HH:mm:ss'
         }
      }
    },
    {
      label: '状态（0正常 1停用）',
      field: 'status',
      form: {
          show: true,
      },
      search: {
         show: true,
         component: 'Select',
         componentProps: {
             option: [{'','请选择字典生成'}]
         }
      }
    },
    {
        label: t('table.action'),
        field: 'action',
        width: '240px',
        form: {
            show: false
        },
        detail: {
            show: false
        }
    }
])

export const { allSchemas } = useCrudSchemas(crudSchemas)