import request from '@/utils/request'

export const pullDownCompany = () =>
  request({
    url: '/company/pulldown',
    method: 'GET'
  })

  export const pullDownDepartment = (data: any) =>
  request({
    url: '/deartment/pulldown',
    method: 'POST',
    data
  })