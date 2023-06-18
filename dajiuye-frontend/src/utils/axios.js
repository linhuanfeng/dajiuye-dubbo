import axios from 'axios'
import { ElMessageBox } from 'element-plus'
import router from '../router'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
// 每个请求前面都会加上/api/，然后被vue.config.js代理拦截，解决跨域
// 浏览器有同源策略，跨域会被拦截，代理服务器不是页面发送请求既没有跨域问题
axios.defaults.baseURL = '/api'
axios.defaults.withCredentials = true
axios.defaults.headers['X-Requested-With'] = 'XMLHttpRequest'
// axios.defaults.headers.Authorization = localStorage.getItem('token') || ''
axios.defaults.headers.post['Content-Type'] = 'application/json'

NProgress.inc(0.2)
NProgress.configure({ easing: 'ease', speed: 600, showSpinner: false })

axios.interceptors.request.use((config) => {
  // 启动进度条
  NProgress.start()
  // 登录授权 请求验证是否有token  需要授权的 API ，必须在请求头中使用 `Authorization` 字段提供 `token` 令牌
  config.headers.Authorization = window.sessionStorage.getItem('token') || ''
  return config
})

axios.interceptors.response.use(res => {
  // console.log('response')
  // console.log(res)
  // if (res === 's') {
  //   ElMessageBox.alert('服务器异常，请稍后再试！')
  //   router.push({ path: '/login' })
  // }
  if (res.status !== 200) {
    ElMessageBox.alert('服务器异常，请稍后再试！')
    console.log(res)
    // router.push({ path: '/login' })
    return Promise.reject(res)
  }
  if (res.data.meta.status === 400) {
    ElMessageBox.alert(res.data.meta.msg)
    router.push({ path: '/login' })
    return Promise.reject(res.data)
  }

  // 关闭进度条
  NProgress.done()

  return res
})

axios.ajax = (options) => {
  return new Promise((resolve, reject) => {
    axios({
      url: options.url,
      method: options.method,
      data: options.data ? options.data : null,
      params: options.params ? options.params : null
    }).then(res => {
      resolve(res)
    }).catch(err => {
      reject(err.data)
    })
  })
}

export default axios
