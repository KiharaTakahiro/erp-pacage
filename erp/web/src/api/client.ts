import request from '@/utils/request'

export const createClient = (data: any) =>
  request({
    url: '/clients/register',
    method: 'post',
    data
  })

  export const editClientPage = (data: any) =>
  request({
    url: '/clients/edit',
    method: 'post',
    data
  })
