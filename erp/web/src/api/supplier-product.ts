import request from '@/utils/request'

export const pullDownSupplierProduct = () =>
  request({
    url: '/supplier-product/pulldown',
    method: 'GET'
  })

export const createSupplierProduct = (data: any) =>
  request({
    url: '/supplier-product/register',
    method: 'post',
    data
  })

export const updateSupplierProduct = (data: any) =>
  request({
    url: '/supplier-product/update',
    method: 'post',
    data
  })

export const getSupplierProduct = (data: any) =>
  request({
    url: '/supplier-product/edit',
    method: 'POST',
    data
  })

export const infoSupplierProduct = (data: any) =>
  request({
    url: '/supplier-product/info',
    method: 'post',
    data
  })