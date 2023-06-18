<template>
<el-container class="container">
  <el-header>
    <div class="header_info">
      <el-avatar shape="circle" fit="contain" :size="80" :src="data.logo"></el-avatar>
      <span>大就业后台管理系统</span>
    </div>
    <el-button class="header_logout" @click="logout" type="info">退出登录</el-button>
  </el-header>
  <el-container>
    <el-aside :width="isCollapse ? '61px' : '200px'">
      <div class="toggle-button" @click="toggleCollpase">|||</div>
      <el-menu
        background-color="#333744"
        text-color="#fff"
        active-text-color="#409EFF"
        unique-opened
        :default-active="data.activePath"
        class="el-menu-vertical-demo"
        :collapse-transition="false"
        :collapse="isCollapse"
        @open="handleOpen"
        @close="handleClose"
        :router="true"
      >
      <!-- 一级菜单 -->
        <el-sub-menu :index="item.id+''"
        v-for="(item) in data.icomenuList"
        :key="item.id"
        >
            <template #title>
              <!-- 图标 -->
              <i :class="data.iconsObj[item.id]"></i>
              <span>{{ item.authName }}</span>
            </template>
            <!-- 二级菜单 -->
            <el-menu-item-group>
              <el-menu-item
                v-for="sunItem in item.children"
                :key="sunItem.id"
                :index="'/' + sunItem.path"
                @click="saveActivePath('/' + sunItem.path)"
                ><!-- 二级菜单的模板区域 -->
                <el-icon><Menu /></el-icon>{{sunItem.authName}}
              </el-menu-item>
            </el-menu-item-group>
        </el-sub-menu>
      </el-menu>
    </el-aside>
    <el-main>
        <!-- 路由占位符 -->
        <router-view></router-view>
    </el-main>
  </el-container>
</el-container>
</template>
<script setup>
import router from '@/router'
import { reactive, ref, onMounted } from 'vue'
import axios from '@/utils/axios'
import { Menu } from '@element-plus/icons-vue'

const isCollapse = ref(false)
const handleOpen = (key, keyPath) => {
  console.log(key, keyPath)
}
const handleClose = (key, keyPath) => {
  console.log(key, keyPath)
}
const data = reactive({
  logo: require('@/assets/logo.png'),
  icomenuList: [],
  iconsObj: {
    // 一级菜单的icon图标
    147: 'el-icon-magic-stick',
    125: 'iconfont icon-users',
    103: 'iconfont icon-tijikongjian',
    101: 'iconfont icon-shangpin',
    102: 'iconfont icon-danju',
    145: 'iconfont icon-baobiao'
  },
  activePath: ''
})
onMounted(() => {
  // console.log('onMounted--Home')
  getMenuList()
  data.activePath = window.sessionStorage.getItem('activePath')
})

const getMenuList = async () => {
  const { data: res } = await axios.ajax({
    url: '/menus',
    method: 'GET'
  })
  // console.log(res)
  data.icomenuList = res.data
}

const logout = () => {
  localStorage.removeItem('token')
  window.sessionStorage.clear()
  router.push({ path: '/login' })
}

const saveActivePath = (activePath) => {
  window.sessionStorage.setItem('activePath', activePath)
  data.activePath = activePath
  // console.log(this.$route.path)
}

// 点击按钮,切换菜单的折叠和展开
const toggleCollpase = () => {
  isCollapse.value = !isCollapse.value
}

</script>
<style lang="less" scoped>
.toggle-button {
  background: #4a5064;
  font-size: 10px;
  text-align: center;
  line-height: 24px;
  color: #fff;
  letter-spacing: 0.2em;
  cursor: pointer;
}
.iconfont{
  padding-right: 10px;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
  background-color: #333744;
  .el-menu{
    background-color: #333744 !important;
  }
}
.container{
  height: 100%;
  // background: rgb(245, 247, 249);
  .el-header{
    color: #fff;
    font-size: 20px;
    background-color: #373d3f;
    display: flex;
    justify-content: space-between;
    align-items: center;
    .header_info{
      height: 100%;
      display: flex;
      align-items: center;
      .el-avatar{
        height: 100%;
        margin-right: 15px;
      }
    }
    .header_logout{

    }
  }
  .el-container{
    .el-aside{
      background-color: #333744;
      .el-radio-group{
        display: flex;
        justify-content: center;
        align-items: center;
        width: 199px;
      }
    }
    .el-main{
      background-color: #eaedf1;
    }
  }
}
</style>
