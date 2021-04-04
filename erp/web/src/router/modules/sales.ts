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
      path: 'received-order',
      component: () => import('@/views/sales/received-order.vue'),
      name: 'receivedOrder',
      meta: { title: 'receivedOrder' }
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
