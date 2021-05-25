import request from '@/utils/request'

export const createClient = (data: any) =>
  request({
    url: '/client/create',
    method: 'post',
    data
  })