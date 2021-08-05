import request from '@/utils/request'

export const infoSupplierProduct = (data: any) =>
  request({
    url: '/supplier-product/info',
    method: 'post',
    data
  })