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
    },{
      path: 'save-users',
      component: () => import('@/views/system/save-users.vue'),
      name: 'users',
      meta: { title: 'createUsre', hidden: true  }
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
      meta: {title: 'newClient', hidden: true },
    },

  ]
}

export default systemRoutes
