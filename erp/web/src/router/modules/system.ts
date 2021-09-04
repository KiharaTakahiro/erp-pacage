import { RouteConfig } from 'vue-router'
import Layout from '@/layout/index.vue'

const systemRoutes: RouteConfig = {
  path: '/system',
  component: Layout,
  redirect: '/system/users',
  name: 'System',
  meta: {
    title: 'system',
    icon: 'user'
  },
  children: [
    {
      path: 'users',
      component: () => import('@/views/system/users.vue'),
      name: 'users',
      meta: { title: 'users' }
    }, {
      path: 'save-users',
      component: () => import('@/views/system/save-users.vue'),
      name: 'users',
      meta: { title: 'createUsre', hidden: true }
    },
    {
      path: 'clinet',
      component: () => import('@/views/system/client.vue'),
      name: 'client',
      meta: { title: 'client' }
    },
    {
      path: 'save-client',
      component: () => import('@/views/system/save-client.vue'),
      name: 'save-client',
      meta: { title: 'newClient', hidden: true }
    },
    {
      path: 'edit-client',
      component: () => import('@/views/system/edit-client.vue'),
      name: 'edit-client',
      meta: { title: 'editClient', hidden: true }
    },
    {
      path: 'supplier-product',
      component: () => import('@/views/system/supplier-product.vue'),
      name: 'supplier-product',
      meta: { title: 'supplierProduct' }
    },
    {
      path: 'save-supplier-product',
      component: () => import('@/views/system/save-supplier-product.vue'),
      name: 'save-supplier-product',
      meta: { title: 'supplierProduct', hidden: true }
    },
    {
      path: 'edit-supplier-product',
      component: () => import('@/views/system/edit-supplier-product.vue'),
      name: 'edit-supplier-product',
      meta: { title: 'editSupplierProduct', hidden: true }
    },
    {
      path: 'product',
      component: () => import('@/views/system/product.vue'),
      name: 'product',
      meta: { title: 'product' }
    },
    {
      path: 'save-product',
      component: () => import('@/views/system/save-product.vue'),
      name: 'save-product',
      meta: { title: 'newProduct', hidden: true }
    },
    {
      path: 'edit-product',
      component: () => import('@/views/system/edit-product.vue'),
      name: 'edit-product',
      meta: { title: 'editProduct', hidden: true }
    }

  ]
}

export default systemRoutes
