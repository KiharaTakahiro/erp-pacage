import request from '@/utils/request'

export const getCode = (data: any) =>
  request({
    url: '/system/getcode',
    method: 'post',
    data
  })