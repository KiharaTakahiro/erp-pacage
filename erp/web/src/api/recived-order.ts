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

  export const getRecivedOrder = (data: any) =>
  request({
    url: '/recivedOrder/edit',
    method: 'post',
    data
  })

  export const updateRecivedOrder = (data: any) =>
  request({
    url: '/recivedOrder/update',
    method: 'post',
    data
  })