import request from '@/utils/request'

export const infoProduct = (data: any) =>
request({
  url: '/product/info',
  method: 'post',
  data
})