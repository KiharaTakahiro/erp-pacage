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
      component: () => import('@/views/system/user/user.vue'),
      name: 'users',
      meta: { title: 'users' },
      children: [
        {
          path: 'user-list',
          component: () => import('@/views/system/user/user-list.vue'),
          name: 'userList',
          meta: { title: 'createUsre' }
        },
        {
          path: 'save-users',
          component: () => import('@/views/system/user/save-users.vue'),
          name: 'saveUser',
          meta: { title: 'createUsre' }
        },
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
      meta: { title: 'supplierProduct' }
    },
    {
      path: 'save-supplier-product',
      component: () => import('@/views/system/supplier-product/save-supplier-product.vue'),
      name: 'save-supplier-product',
      meta: { title: 'supplierProduct', hidden: true }
    },
    {
      path: 'edit-supplier-product',
      component: () => import('@/views/system/supplier-product/edit-supplier-product.vue'),
      name: 'edit-supplier-product',
      meta: { title: 'editSupplierProduct', hidden: true }
    },
    {
      path: 'product',
      component: () => import('@/views/system/product/product.vue'),
      name: 'product',
      meta: { title: 'product' }
    },
    {
      path: 'save-product',
      component: () => import('@/views/system/product/save-product.vue'),
      name: 'save-product',
      meta: { title: 'newProduct', hidden: true }
    },
    {
      path: 'edit-product',
      component: () => import('@/views/system/product/edit-product.vue'),
      name: 'edit-product',
      meta: { title: 'editProduct', hidden: true }
    }
  ]
}

export default systemRoutes
