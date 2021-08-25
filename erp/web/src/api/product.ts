import request from '@/utils/request'

export const pullDownProduct = () =>
  request({
    url: '/product/pulldown',
    method: 'GET'
  })

  
export const getProduct = (data: JSON) =>
request({
  url: '/product/edit',
  method: 'POST',
  data
})