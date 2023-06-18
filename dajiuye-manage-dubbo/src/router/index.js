import { createRouter, createWebHistory } from 'vue-router'
import Login from '../components/login/Login'
import Home from '../components/home/Home'
import Users from '../components/home/users/Users'
import Welcome from '../components/home/welcome/Welcome'
import Rights from '../components/home/power/rights/Rights'
import Roles from '../components/home/power/roles/Roles'
import Cate from '../components/home/goods/cate/Cate'
import Params from '../components/home/goods/params/Params'
import Add from '../components/home/goods/list/children/Add'
import List from '../components/home/goods/list/List'
import Order from '../components/home/order/Order'
import Report from '../components/home/report/Report'
const routerHistory = createWebHistory()
const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/home',
    component: Home,
    redirect: '/welcome',
    children: [
      {
        path: '/welcome',
        component: Welcome
      },
      {
        path: '/users',
        component: Users
      },
      {
        path: '/rights',
        component: Rights
      },
      {
        path: '/roles',
        component: Roles
      },
      {
        path: '/categories',
        component: Cate
      },
      {
        path: '/params',
        component: Params
      },
      {
        path: '/goods',
        component: List
      },
      {
        path: '/goods/add',
        component: Add
      },
      {
        path: '/orders',
        component: Order
      },
      {
        path: '/reports',
        component: Report
      }
    ]
  }
]
const router = createRouter({
  history: routerHistory,
  routes
})

router.beforeEach((to, from, next) => {
  // to 将访问哪一个路径
  // from 代表从哪个路径跳转而来
  // next 是一个函数,表示放行
  //   next() 放行 next('/login') 强制跳转
  if (to.path === '/login') return next()
  // 获取token
  // const token = window.sessionStorage.getItem('token')
  // if (token) return next('/login')
  next()
})

export default router
