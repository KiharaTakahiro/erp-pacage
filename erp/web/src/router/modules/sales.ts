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
      path: 'recieved-order',
      component: () => import('@/views/sales/recieved-order.vue'),
      name: 'recievedOrder',
      meta: { title: 'recievedOrder'}
    },
    {
      path: 'save-recieved-order',
      component: () => import('@/views/sales/save-recieved-order.vue'),
      name: 'save-recievedOrder',
      meta: { title: 'newRecievedOrder', hidden: true }
    },
    {
      path: 'invoice',
      component: () => import('@/views/sales/invoice.vue'),
      name: 'invoice',
      meta: { title: 'invoice' }
    },
  ]
}

export default salesRoutes
