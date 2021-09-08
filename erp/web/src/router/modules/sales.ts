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
      component: () => import('@/views/sales/recived-order.vue'),
      name: 'recivedOrder',
      meta: { title: 'recivedOrder' }
    },
    {
      path: 'save-recived-order',
      component: () => import('@/views/sales/save-recived-order.vue'),
      name: 'save-recivedOrder',
      meta: { title: 'newRecievedOrder', hidden: true }
    },
    {
      path: 'invoice',
      component: () => import('@/views/sales/invouce/invoice.vue'),
      name: 'invoice',
      meta: { title: 'invoice' }
    }
  ]
}

export default salesRoutes
