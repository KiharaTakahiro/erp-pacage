import request from '@/utils/request'

export const pullDownSupplier = () =>
  request({
    url: '/supplier/pulldown',
    method: 'GET'
  })

export const createSupplier = (data: any) =>
  request({
    url: '/supplier/register',
    method: 'post',
    data
  })

export const updateSupplier = (data: any) =>
  request({
    url: '/supplier/update',
    method: 'post',
    data
  })

export const getSupplier = (data: any) =>
  request({
    url: '/supplier/edit',
    method: 'POST',
    data
  })

export const infoSupplier = (data: any) =>
  request({
    url: '/supplier/info',
    method: 'post',
    data
  })