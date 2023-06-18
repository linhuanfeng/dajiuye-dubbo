<template>
  <div class="users">
    <Breadcrumb :name1="data.name1" :name2="data.name2"></Breadcrumb>
        <!-- 卡片视图区域 -->
    <el-card class="box-card">
      <el-row :gutter="20">
        <el-col :span="7">
          <!-- 搜索与添加区域 -->
          <el-input placeholder="请输入内容" clearable v-model="data.queryInfo.query" @clear="getUserList">
            <el-button icon="el-icon-search" @click="getUserList"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <!-- 添加用户区域 -->
          <el-button type="primary" @click="data.addDialogVisible = true">用户添加</el-button>
        </el-col>
      </el-row>
      <!-- 用户列表 -->
      <el-table :data="data.userData.userList" stripe style="width: 100%">
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column prop="username" label="姓名"></el-table-column>
        <el-table-column prop="email" label="邮箱"></el-table-column>
        <el-table-column prop="mobile" label="电话"></el-table-column>
        <el-table-column prop="role_name" label="角色"></el-table-column>
        <el-table-column label="状态">
          <template v-slot="scope">
            <el-switch v-model="scope.row.mg_state" active-color="#13ce66" inactive-color="#ff4949" @change="userStatuChanged_(scope.row)"> </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180px">
          <template v-slot="scope">
            <!-- 修改按钮 -->
            <el-button type="primary" :icon="Edit" size="small" @click="showEditDialog_(scope.row.id)"></el-button>
            <!-- 删除按钮 -->
            <el-button type="danger" :icon="Delete" size="small" @click="removeUserById(scope.row.id)"></el-button>
            <!-- 分配角色按钮 -->
            <el-tooltip effect="dark" content="分配角色" placement="top" :enterable="false">
              <el-button type="warning" :icon="Edit" size="small" @click="setRoles_(scope.row)"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="data.queryInfo.pagenum"
        @size-change="handleSizeChange"
        :page-size="data.queryInfo.pagesize"
        :page-sizes="[5, 9, 13, 15]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="data.userData.total"
      >
      </el-pagination>
    </el-card>
        <!-- 添加用户对话框 -->
    <el-dialog title="添加用户" v-model="data.addDialogVisible" width="50%" :before-close="addDislogClosed">
      <!-- 内容主题区域 -->
      <el-form label-width="70px" ref="addFormRef" :model="data.addForm" :rules="data.addFormRules">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="data.addForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="data.addForm.password"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="data.addForm.email"></el-input>
        </el-form-item>
        <el-form-item label="手机" prop="mobile">
          <el-input v-model="data.addForm.mobile"></el-input>
        </el-form-item>
      </el-form>
      <!-- 底部按钮区域 -->
      <span  class="dialog-footer">
        <el-button @click="data.addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUser">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 修改用户信息对话框 -->
    <el-dialog title="修改用户" @close="aditClosed_" v-model:visible="data.editDialogVisble" width="50%">
      <el-form :model="editForm" :rules="addFormRules" ref="editFormRef" label-width="70px">
        <el-form-item label="用户名">
          <el-input v-model="editForm.username" disabled></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="editForm.email"></el-input>
        </el-form-item>
        <el-form-item label="手机" prop="mobile">
          <el-input v-model="editForm.mobile"></el-input>
        </el-form-item>
      </el-form>
      <span class="dialog-footer">
        <el-button @click="data.editDialogVisble = false">取 消</el-button>
        <el-button type="primary" @click="editUserInfo_">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 分配角色 -->
    <el-dialog title="分配角色" v-model:visible="data.setRolesDialogVisible" @close="setRolesDialogClosed_" width="50%">
      <div>
        <p>当前的用户 : {{ userInfo.username }}</p>
        <p>当前的角色 : {{ userInfo.role_name }}</p>
        <p>
          分配新角色:
          <el-select v-model="selectRoleId" placeholder="请选择">
            <el-option v-for="item in rolesList" :key="item.id" :label="item.roleName" :value="item.id"> </el-option>
          </el-select>
        </p>
      </div>
      <span  class="dialog-footer">
        <el-button @click="data.setRolesDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveRolesInfo_">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script setup>
import Breadcrumb from '@/components/content/breadcrumb/Breadcrumb.vue'
import { reactive } from 'vue-demi'
import { onMounted } from 'vue'
import { Delete, Edit } from '@element-plus/icons-vue'
import { ElMessageBox, ElMessage } from 'element-plus'
import axios from '@/utils/axios'

const data = reactive({
  name1: '2',
  name2: '2',
  // 获取用户列表的参数对象
  queryInfo: {
  // 搜索值
    query: '',
    // 当前的页数
    pagenum: 1,
    // 当前每次显示多少条数据
    pagesize: 5
  },
  // 存放用户的数据和数量
  userData: {
    userList: [],
    total: 0
  },
  // 控制用户对话框的显示和隐藏
  addDialogVisible: false,
  // 添加用户数据的对象
  addForm: {
    username: '',
    password: '',
    email: '',
    mobile: ''
  },
  // 修改用户消息对话框显示和隐藏
  editDialogVisble: false,
  // 控制分配角色对话框的显示和隐藏
  setRolesDialogVisible: false,
  // 需要被分配角色的用户信息
  userInfo: {},
  // 分配角色列表
  rolesList: [],
  // 保存已经选中的角色id值
  selectRoleId: '',
  // 查询用户的对象
  editForm: {}
})

onMounted(async () => {
  console.log('sadf')
  getUserList()
})

const getUserList = async () => {
  const { data: res } = await axios.ajax({
    url: '/users',
    method: 'GET',
    params: data.queryInfo
  })
  console.log('getUserList')
  console.log(res)
  if (res.meta.status === 200) {
    data.userData.userList = res.data.users
    data.userData.total = res.data.total
  } else {
    console.log('获取失败')
  }
}

// 监听 pagesize 改变事件 每页显示的个数
const handleSizeChange = (newSize) => {
  // console.log(newSize)
  data.queryInfo.pagesize = newSize
  getUserList()
}
// 监听 页码值 改变的事件 当前页面值
const handleCurrentChange = (newPage) => {
  console.log(newPage)
  data.queryInfo.pagenum = newPage
  getUserList()
}
// 监听Switch状态的改变
const userStatuChanged_ = async (userInfo) => {
  // console.log(userInfo)
  const { data: res } = await axios.ajax({
    url: 'userStatuChanged',
    data: userInfo
  })
  if (res.meta.status !== 200) {
    userInfo.mg_state = !userInfo.mg_state
    return this.$message.error('更新用户状态失败!')
  }
  return this.$message.success('更新用户状态成功!')
}
// 监听添加用户的对话框关闭事件
const addDislogClosed = (done) => {
  console.log('addDislogClosed')
  done()
  // ElMessageBox.confirm('Are you sure to close this dialog?')
  //   .then(() => {
  //     done()
  //   })
  //   .catch(() => {
  //     // catch error
  //   })
}
// 点击按钮,添加新用户
const addUser = async () => {
  // this.$refs.addFormRef.validate(async valid => {
  // if (!valid) return
  // 可以发起添加用户请求
  const { data: res } = await axios.ajax({
    url: '/users',
    method: 'POST',
    data: data.addForm
  })
  console.log('addUser')
  console.log(res)
  if (res.meta.status !== 201) {
    ElMessageBox.error('用户添加失败了~')
  } else {
    ElMessage({
      message: '用户添加成功了~',
      type: 'success'
    })
  }
  // 隐藏添加用户的对话框
  data.addDialogVisible = false
  // 添加成后重新获取用户数据,不需要用户手动刷新
  getUserList()
  // })
}
// 展示编辑用于的对话框
const showEditDialog_ = async (id) => {
  const { data: res } = await axios({
    url: 'showEditDialog',
    param: id
  })
  if (res.meta.status !== 200) {
    return this.$message.error('查询用户数据失败~')
  }
  this.editForm = res.data
  console.log(res)
  data.editDialogVisble = true
  return this.$message.success('查询用户数据成功~')
}
// 监听修改用户对话框的关闭事件
const aditClosed_ = () => {
  this.$refs.editFormRef.resetFields()
}
const editUserInfo_ = () => {
  this.$refs.editFormRef.validate(async valid => {
    console.log(valid)
    if (!valid) return
    // 发起修改用户信息的数据请求
    const { data: res } = await this.$http.put('users/' + data.editForm.id, {
      email: data.editForm.email,
      mobile: data.editForm.mobile
    })
    if (res.meta.status !== 200) {
      this.$message.error('更新用户信息失败!')
    }
    data.editDialogVisble = false
    getUserList()
    this.$message.success('更新用户信息成功!')
  })
}
// 根据id删除对应的用户信息
const removeUserById = async (id) => {
  // 询问用户是否删除用户
  ElMessageBox.confirm(
    '此操作将永久删除该用户, 是否继续?',
    'Warning',
    {
      confirmButtonText: 'OK',
      cancelButtonText: 'Cancel',
      type: 'warning'
    }
  )
    .then(async () => {
      const { data: res } = await axios.ajax({
        url: '/users/' + id,
        method: 'DELETE'
      })
      if (res.meta.status === 200) {
        ElMessage({
          type: 'success',
          message: res.meta.msg
        })
        getUserList()
      } else {
        ElMessage({
          type: 'error',
          message: res.meta.msg
        })
      }
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: 'Delete canceled'
      })
    })
}

// 展示分配角色的对话框
const setRoles_ = async (userInfo) => {
  this.userInfo = userInfo
  // 再展示对话框之前获取所有的角色列表
  const { data: res } = await axios.ajax({
    url: 'setRoles'
  })
  if (res.meta.status !== 200) {
    return this.$message.error('获取角色列表失败!')
  }
  this.rolesList = res.data
  this.setRolesDialogVisible = true
}
// 点击按钮,分配角色
const saveRolesInfo_ = async () => {
  if (!this.selectRoleId) {
    return this.$message.error('请选择要分配的角色!')
  }
  const { data: res } = await axios.ajax({
    url: 'saveRolesInfo',
    data: data.userInfo
  })
  if (res.meta.status !== 200) {
    return this.$message.error('更新角色失败!')
  }
  // this.$message.success('更新角色成功!')
  // this.getUserList()
  // this.setRolesDialogVisible = false
}
// 分配角色对话框关闭
const setRolesDialogClosed_ = () => {
  this.selectRoleId = ''
  this.userInfo = ''
}

</script>
<style lang="less" scoped>
  .el-card{
    margin-top: 10px;
  }
</style>
