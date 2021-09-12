import request from '@/utils/request'

export const createWarehouse = (data: any) =>
  request({
    url: '/warehouse/register',
    method: 'post',
    data
  })