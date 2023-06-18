<template>
  <div class="rights">
    <Breadcrumb name1="权限管理" name2="权限列表" />
    <!-- 卡片视图 -->
    <el-card class="box-card">
      <el-table :data="data.rightsList" stripe>
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column prop="authName" label="权限名称"> </el-table-column>
        <el-table-column prop="path" label="路径"> </el-table-column>
        <el-table-column label="权限等级">
          <template v-slot="scope">
            <el-tag v-if="scope.row.level === '0'">一级</el-tag>
            <el-tag type="success" v-else-if="scope.row.level === '1'">二级</el-tag>
            <el-tag type="warning" v-else-if="scope.row.level === '2'">三级</el-tag>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import Breadcrumb from '@/components/content/breadcrumb/Breadcrumb.vue'
import { onMounted } from 'vue'
import { reactive } from 'vue-demi'
import axios from '@/utils/axios'

const data = reactive({
  // 权限列表
  rightsList: []
})
onMounted(() => {
  getRightsList()
})
const getRightsList = async () => {
  const { data: res } = await axios.ajax({
    url: 'rights/list',
    method: 'GET'
  })
  data.rightsList = res.data
}
</script>

<style lang="less" scoped></style>
