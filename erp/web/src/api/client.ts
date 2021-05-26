import request from '@/utils/request'

export const createClient = (data: any) =>
  request({
    url: '/clients/register',
    method: 'post',
    data
  })