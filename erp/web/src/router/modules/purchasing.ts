import { RouteConfig } from 'vue-router'
import Layout from '@/layout/index.vue'

const purchasingRoutes: RouteConfig = {
  path: '/purchasing',
  component: Layout,
  redirect: '/purchasing/quotation',
  name: 'Purchasing',
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

export default purchasingRoutes