import request from '@/utils/request'

export const createProduct = (data: any) =>
request({
  url: '/product/register',
  method: 'post',
  data
})

export const infoProduct = (data: any) =>
request({
  url: '/product/info',
  method: 'post',
  data
})