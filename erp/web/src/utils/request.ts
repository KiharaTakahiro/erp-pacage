import axios from 'axios'
import { Message } from 'element-ui'
import { UserModule } from '@/store/modules/user'

const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  timeout: 5000
  // withCredentials: true // send cookies when cross-domain requests
})

// Request interceptors
service.interceptors.request.use(
  (config) => {
    // Add X-Access-Token header to every request, you can add other custom headers here
    if (UserModule.token) {
      config.headers['X-Access-Token'] = UserModule.token
    }
    return config
  },
  (error) => {
    Promise.reject(error)
  }
)

// Response interceptors
service.interceptors.response.use(
  (response) => {
    const res = response.data
    if (response.status !== 200) {
      let messages = res.message || 'Error'
      // 期限切れの場合はBAD REQUESTとなるのでその場合はトークンリセット
      // BAD REQUESTの場合に問答無用で遷移してしまうのが良いかは要検討
      if (response.status === 400) {
        UserModule.ResetToken()
        location.reload() // To prevent bugs from vue-router
      }
      Message({
        message: res.message || 'Error',
        type: 'error',
        duration: 5 * 1000
      })
      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      return response
    }
  },
  (error) => {
    if (error.response.status === 400) {
      UserModule.ResetToken()
      location.reload() // To prevent bugs from vue-router
    }
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
