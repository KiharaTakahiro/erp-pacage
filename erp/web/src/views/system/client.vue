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
    <div class="right">
      <el-select v-model="value" placeholder="Select" >
      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value">
      </el-option>
      </el-select>
      <el-input
        placeholder="Type something"
        prefix-icon="el-icon-search"
        v-model="searchName"
        >
      </el-input>
    </div>
    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        label=""
        width="180">
        <el-radio v-model="radio" label=""></el-radio>
      </el-table-column>
      <el-table-column
        prop="id"
        label="ID"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="Client name">
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

  private options = [{
      value: '',
      label: ''
    },{
      value: '1',
      label: '1'
    },{
      value: '2',
      label: '2'
    }, {
      value: '3',
      label: '3'
    }, {
      value: '4',
      label: '4'
    }]
  value = ''
  searchName = ''

  
  private tableData = [{
    id: '1',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles'
  }, {
    id: '2',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles'
  }, {
    id: '3',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles'
  }, {
    id: '4',
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