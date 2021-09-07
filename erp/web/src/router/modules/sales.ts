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
      component: () => import('@/views/sales/quotation.vue'),
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
          meta: { title: 'newRecievedOrder' }
        },
      ]
    },
    
    {
      path: 'invoice',
      component: () => import('@/views/sales/invoice.vue'),
      name: 'invoice',
      meta: { title: 'invoice' }
    }
  ]
}

export default salesRoutes
