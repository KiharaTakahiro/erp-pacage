<template>
  <div class="app-container">
    <div>{{ $t("route.client") }}</div>
    <el-button
        type="primary"
        style="width:13%; margin-bottom:30px; margin-top:30px;"
        @click.native.prevent="createClientBtn"
      >
        {{ $t('client.add') }}
    </el-button>
    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        prop="date"
        label="Date"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="Name"
        width="180">
      </el-table-column>
      <el-table-column
        prop="address"
        label="Address">
      </el-table-column>
    </el-table>
    <div class="left">
      <back-btn/>
    </div>
    <div class="right">
      <el-button
        type="primary"
        style="width:100%; margin-bottom:30px; margin-top:30px;"
        @click.native.prevent="editClientBtn"
      >
        {{ $t('client.edit') }}
      </el-button>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'
import { Form as ElForm, Input } from 'element-ui'
import { ClientModule } from '@/store/modules/client'
import '@/assets/custom-theme/index.css'
import backBtn from "@/views/components/back-button.vue"



@Component({
  name: 'Client',
  components :{
    backBtn
  }
})
export default class extends Vue {

  client = {
    id: '2'
    }
  
  private tableData = [{
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles'
  }, {
    date: '2016-05-02',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles'
  }, {
    date: '2016-05-04',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles'
  }, {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles'
  }]

  createClientBtn() {
    // ボタンが押されたときの処理
    this.$router.push({
    path:'save-client'
    }).catch(err => {
      console.warn(err)
    })
  }
  editClientBtn() {
    // ボタンが押されたときの処理
      ClientModule.EditClient(this.client)
      this.$router.push({
      path:'edit-client'
      }).catch(err => {
        console.warn(err)
      })
  }
}
</script>

<style lang="scss" scoped>
.field-label {
  vertical-align: middle;
}

.box-card {
  width: 400px;
  max-width: 100%;
  margin: 20px auto;
}

.block {
  padding: 30px 24px;
}

.tag-item {
  margin-right: 15px;
}

.right {
  float: right;
} 

.left {
  float: left;
}
</style>