import request from '@/utils/request'

export const pullDownWarehouse = () =>
  request({
    url: '/warehouse/pulldown',
    method: 'GET'
  })

export const createWarehouse = (data: any) =>
  request({
    url: '/warehouse/register',
    method: 'post',
    data
  })

export const updateWarehouse = (data: any) =>
  request({
    url: '/warehouse/update',
    method: 'post',
    data
  })

export const getWarehouse = (data: any) =>
  request({
    url: '/warehouse/edit',
    method: 'POST',
    data
  })

export const infoWarehouse = (data: any) =>
  request({
    url: '/warehouse/info',
    method: 'post',
    data
  })