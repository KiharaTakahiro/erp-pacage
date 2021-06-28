import request from '@/utils/request'

export const createClient = (data: any) =>
  request({
    url: '/clients/register',
    method: 'post',
    data
  })

  export const getClient = (data: any) =>
  request({
    url: '/clients/edit',
    method: 'post',
    data
  })

  export const editedClient = (data: any) =>
  request({
    url: '/client/edited',
    method: 'post',
    data
  })