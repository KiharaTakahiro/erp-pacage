import request from '@/utils/request'

export const infoSupplierProduct = (data: any) =>
  request({
    url: '/supplier-products/info',
    method: 'post',
    data
  })