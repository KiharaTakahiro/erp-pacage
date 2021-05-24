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
    },
    {
      path: 'clinet',
      component: () => import('@/views/system/client.vue'),
      name: 'client',
      meta: { title: 'client' }
    },
    {
      path: 'save-cline',
      component: () => import('@/views/system/save-client.vue'),
      name: 'save-client',
      meta: { hidden: true }
    },

  ]
}

export default systemRoutes
