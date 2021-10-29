import { RouteConfig } from 'vue-router'
import Layout from '@/layout/index.vue'

const salesRoutes: RouteConfig = {
  path: '/sales',
  component: Layout,
  redirect: '/sales/quotation',
  name: 'Sales',
  meta: {
    title: 'sales',
    icon: 'shopping'
  },
  children: [
    {
      path: 'quotation',
      component: () => import('@/views/sales/quotation/quotation.vue'),
      name: 'quotation',
      meta: { title: 'quotation' }
    },
    {
      path: 'recived-order',
      component: () => import('@/views/sales/recived-order/recived-order.vue'),
      name: 'recivedOrder',
      meta: { title: 'recivedOrder' },
      redirect: '/sales/recived-order/recived-order-list',
      children: [
        {
          path: 'recived-order-list',
          component: () => import('@/views/sales/recived-order/recived-order-list.vue'),
          name: 'recivedOrder-list',
          meta: { title: 'recivedOrderList' }
        },
        {
          path: 'save-recived-order',
          component: () => import('@/views/sales/recived-order/save-recived-order.vue'),
          name: 'save-recivedOrder',
          meta: { title: 'newRecivedOrder' }
        },
        {
          path: 'edit-recived-order',
          component: () => import('@/views/sales/recived-order/edit-recived-order.vue'),
          name: 'edit-recived-order',
          meta: {title: 'editRecivedOrder' , hidden: true}
        }
      ]
    },
    {
      path: 'clinet',
      component: () => import('@/views/system/client/client.vue'),
      name: 'client',
      meta: { title: 'client' },
      children: [
        {
          path: 'client-list',
          component: () => import('@/views/system/client/client-list.vue'),
          name: 'client-list',
          meta: { title: 'clientList' }
        },
        {
          path: 'save-client',
          component: () => import('@/views/system/client/save-client.vue'),
          name: 'save-client',
          meta: { title: 'newClient' }
        },
        {
          path: 'edit-client',
          component: () => import('@/views/system/client/edit-client.vue'),
          name: 'edit-client',
          meta: { title: 'editClient', hidden: true }
        },
      ]
    },
    
    
    {
      path: 'supplier-product',
      component: () => import('@/views/system/supplier-product/supplier-product.vue'),
      name: 'supplier-product',
      meta: { title: 'supplierProduct' },
      children: [
        {
          path: 'supplier-product-list',
          component: () => import('@/views/system/supplier-product/supplier-product-list.vue'),
          name: 'save-supplier-product',
          meta: { title: 'supplierProductList' }
        },
        {
          path: 'save-supplier-product',
          component: () => import('@/views/system/supplier-product/save-supplier-product.vue'),
          name: 'save-supplier-product',
          meta: { title: 'saveSupplierProduct' }
        },
        {
          path: 'edit-supplier-product',
          component: () => import('@/views/system/supplier-product/edit-supplier-product.vue'),
          name: 'edit-supplier-product',
          meta: { title: 'editSupplierProduct' ,hidden: true}
        },
      ]
    },
    {
      path: 'supplier',
      component: () => import('@/views/system/supplier/supplier.vue'),
      name: 'supplier',
      meta: { title: 'supplier' },
      children: [
        {
          path: 'supplier-list',
          component: () => import('@/views/system/supplier/supplier-list.vue'),
          name: 'supplier-list',
          meta: { title: 'supplierList' }
        },
        {
          path: 'save-supplier',
          component: () => import('@/views/system/supplier/save-supplier.vue'),
          name: 'save-supplier',
          meta: { title: 'newSupplier' }
        },
        {
          path: 'edit-supplier',
          component: () => import('@/views/system/supplier/edit-supplier.vue'),
          name: 'edit-supplier',
          meta: { title: 'editSupplier', hidden: true }
        }
      ]
    },
    {
      path: 'product',
      component: () => import('@/views/system/product/product.vue'),
      name: 'product',
      meta: { title: 'product' },
      children: [
        {
          path: 'product-list',
          component: () => import('@/views/system/product/product-list.vue'),
          name: 'product-list',
          meta: { title: 'productList' }
        },
        {
          path: 'save-product',
          component: () => import('@/views/system/product/save-product.vue'),
          name: 'save-product',
          meta: { title: 'newProduct' }
        },
        {
          path: 'edit-product',
          component: () => import('@/views/system/product/edit-product.vue'),
          name: 'edit-product',
          meta: { title: 'editProduct', hidden: true }
        }
      ]
    },
    {
      path: 'warehouse',
      component: () => import('@/views/system/warehouse/warehouse.vue'),
      name: 'warehouse',
      meta: { title: 'warehouse' },
      children: [
        {
          path: 'warehouse-list',
          component: () => import('@/views/system/warehouse/warehouse-list.vue'),
          name: 'warehouse-list',
          meta: { title: 'warehouseList' }
        },
        {
          path: 'save-warehouse',
          component: () => import('@/views/system/warehouse/save-warehouse.vue'),
          name: 'save-warehouse',
          meta: { title: 'newWarehouse' }
        },
        {
          path: 'edit-warehouse',
          component: () => import('@/views/system/warehouse/edit-warehouse.vue'),
          name: 'edit-warehouse',
          meta: { title: 'editWarehouse', hidden: true }
        }
      ]
    },
    {
      path: 'invoice',
      component: () => import('@/views/sales/invoice/invoice.vue'),
      name: 'invoice',
      meta: { title: 'invoice' }
    }
  ]
}

export default salesRoutes
