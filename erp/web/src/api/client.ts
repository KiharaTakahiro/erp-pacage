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

  export const updateClient = (data: any) =>
  request({
    url: '/client/update',
    method: 'post',
    data
  })

  export const infoClient = (data: any) =>
  request({
    url: '/clients/info',
    method: 'post',
    data
  })

  export const pullDownClient = () =>
  request({
    url: '/clients/pulldown',
    method: 'GET'
  })