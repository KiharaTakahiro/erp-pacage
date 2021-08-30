import request from '@/utils/request'

export const createRecivedOrder = (data: any) =>
  request({
    url: '/reivedorder/register',
    method: 'POST',
    data
  })