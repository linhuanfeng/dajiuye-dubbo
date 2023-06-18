<template>
  <div class="login_container">
    <div class="login_box">
          <!--  头像区域-->
          <div class="avatar_box">
          <img src="@/assets/logo.png" alt="" />
        </div>
        <!-- 表单提交区域 -->
        <el-form ref="loginFormRef" :model="data.loginForm"  label-width="0px" class="login_form" >
          <el-form-item prop="name">
            <el-input :prefix-icon="User" v-model="data.loginForm.username"></el-input>
          </el-form-item>
          <el-form-item prop="name">
            <el-input type="password" :prefix-icon="Lock" v-model="data.loginForm.password"></el-input>
          </el-form-item>
          <el-form-item class="login_footer">
            <el-button type="primary" @click="loginSubmit">登录</el-button>
            <el-button>重置</el-button>
          </el-form-item>
        </el-form>
    </div>
  </div>
</template>
<script setup>
import { reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from '@/utils/axios'
import { User, Lock } from '@element-plus/icons-vue'
const router = useRouter()
// 变量
const data = reactive({
  loginForm: {
    username: 'admin',
    password: '123456'
  }
})

onMounted(() => {
  const token = window.sessionStorage.getItem('token')
  if (token) {
    router.push({ path: '/home' })
  }
})

const loginSubmit = async () => {
  const { data: res } = await axios.ajax({
    url: '/login/login',
    method: 'POST',
    data: data.loginForm
  })
  console.log('onSubloginSubmitmit')
  console.log(res)
  window.sessionStorage.setItem('token', res)
  router.push({ path: '/home' })
  // if (res.meta.status === 200) {
  //   console.log('登录成功')
  //   // 登录成功后设置token到vuex中
  //   // localStorage.token = res.data.token
  //   window.sessionStorage.setItem('token', res.data.token)
  //   router.push({ path: '/home' })
  // } else {
  //   console.log('登录失败')
  // }
}

</script>
<style lang="less" scoped>
.login_container {
  height: 100%;
  background-color: #5dbe8a;
}
.login_box {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 450px;
  height: 300px;
  background-color: #fff;
  border-radius: 3px;
  .avatar_box {
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    padding: 10px;
    width: 130px;
    height: 130px;
    border: 1px solid #eee;
    border-radius: 50%;
    box-shadow: 0 0 10px #ddd;
    background-color: #fff;
    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: #eeeeee;
    }
  }
  .login_form {
    box-sizing: border-box;
    position: absolute;
    bottom: 0;
    width: 100%;
    padding: 0 20px;
  }
  .btns {
    display: flex;
    justify-content: flex-end;
  }
}
</style>
