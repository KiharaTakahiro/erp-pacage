import request from '@/utils/request'

export const pullDownProduct = () =>
  request({
    url: '/product/pulldown',
    method: 'GET'
  })
