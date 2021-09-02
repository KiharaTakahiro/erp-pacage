import request from '@/utils/request'

export const createRecivedOrder = (data: any) =>
  request({
    url: '/recivedorder/register',
    method: 'POST',
    data
  })