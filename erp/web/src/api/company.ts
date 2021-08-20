import request from '@/utils/request'

export const pullDownCompany = () =>
  request({
    url: '/company/pulldown',
    method: 'GET'
  })