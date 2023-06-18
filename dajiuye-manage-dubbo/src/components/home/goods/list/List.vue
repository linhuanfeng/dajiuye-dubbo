<!-- eslint-disable no-unused-vars -->
<template>
  <div class="list">
    <Breadcrumb name1="商品管理" name2="商品列表" />
    <el-card>
      <!-- 按钮搜索/添加区域 -->
      <el-row :gutter="20">
        <el-col :span="7">
          <el-input placeholder="请输入内容" @clear="getGoofsList" clearable v-model="data.queryInfo.query">
            <el-button  icon="el-icon-search" @click="getGoofsList"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="goAddpage">添加商品</el-button>
        </el-col>
      </el-row>
      <!-- table区域 -->
      <el-row>
        <el-table :data="data.goodsList" style="width: 100%">
          <el-table-column type="index" label="#"></el-table-column>
          <el-table-column prop="goods_name" label="商品名称"></el-table-column>
          <el-table-column prop="goods_price" label="价格(元)" width="110px"></el-table-column>
          <el-table-column
            prop="goods_number"
            width="120px"
            label="商品数量"
          ></el-table-column>
          <el-table-column prop="goods_weight" label="商品重量" width="130px"></el-table-column>
          <el-table-column prop="add_time" :formatter="formatDate" width="220px" label="创建时间">
            <!-- <template v-slot="scope">
              {{ scope.row.add_time | dataFormat }}
            </template> -->
          </el-table-column>
          <el-table-column label="操作" width="150px">
            <template v-slot="scope">
              <el-button type="primary" size="small" :icon="Edit"/>
              <el-button type="danger" size="small" :icon="Delete" @click="removeById(scope.row.goods_id)"/>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="data.queryInfo.pagenum"
        :page-sizes="[7, 11, 15, 19]"
        :page-size="data.queryInfo.pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="data.total"
      >
      </el-pagination>
    </el-card>
  </div>
</template>

<script setup>
import Breadcrumb from '@/components/content/breadcrumb/Breadcrumb'
import axios from '@/utils/axios'
import { Delete, Edit } from '@element-plus/icons-vue'
import { onMounted, reactive } from 'vue-demi'
const data = reactive({
  // 查询参数对象
  queryInfo: {
    query: '',
    pagenum: 1,
    pagesize: 7
  },
  // 商品列表
  goodsList: [],
  // 总数据条数
  total: 0
})

onMounted(() => {
  getGoofsList()
})
// 根据分页获取列表数据
const getGoofsList = async () => {
  const { data: res } = await axios.ajax({
    url: 'goods',
    params: data.queryInfo
  })

  data.goodsList = res.data.goods
  data.total = res.data.total
  console.log(res.data)
}
// 显示的页数改变
const handleSizeChange = (newSize) => {
  data.queryInfo.pagesize = newSize
  getGoofsList()
}
// 页码的改变
const handleCurrentChange = (newValue) => {
  data.queryInfo.pagenum = newValue
  getGoofsList()
}
const removeById = async (id) => {
  const confirmResult = await this.$confirm('此操作将永久删除该文件, 是否继续?', '删除', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).catch(err => err)
  console.log(confirmResult)
  if (confirmResult !== 'confirm') {
    return this.$message.info('删除操作已取消!')
  }
  const { data: res } = await this.$http.delete('goods/' + id)
  if (res.meta.status !== 200) {
    return this.$message.error('删除失败!')
  }
  this.$message.success('删除成功!')
  this.getGoofsList()
}
const goAddpage = () => {
  this.$router.push('goods/add')
}
const formatDate = (row, column) => {
  // 获取单元格数据
  const data = row[column.property]
  if (data == null) {
    return null
  }
  const dt = new Date(data)
  return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate() + ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds()
}

</script>

<style lang="less" scoped>
.el-table {
  margin: 15px 0 0;
}
</style>
