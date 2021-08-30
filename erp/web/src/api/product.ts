import request from '@/utils/request'

export const pullDownProduct = () =>
  request({
    url: '/product/pulldown',
    method: 'GET'
  })

export const createProduct = (data: any) =>
request({
  url: '/product/register',
  method: 'post',
  data
})

export const getProduct = (data: JSON) =>
request({
  url: '/product/edit',
  method: 'POST',
  data
})

export const infoProduct = (data: any) =>
request({
  url: '/product/info',
  method: 'post',
  data
})