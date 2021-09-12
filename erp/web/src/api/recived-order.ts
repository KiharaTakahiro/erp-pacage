import request from '@/utils/request'

export const createRecivedOrder = (data: any) =>
  request({
    url: '/recivedorder/register',
    method: 'POST',
    data
  })

  export const infoRecivedOrder = (data: any) =>
  request({
    url: '/recivedOrder/info',
    method: 'post',
    data
  })
