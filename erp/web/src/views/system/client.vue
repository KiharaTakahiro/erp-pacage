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
      <el-select clearable  v-model="TargetClientSeq" placeholder="Select">
        <el-option
          v-for="client in clientsData"
          :key="client.clientsSeq"
          :label="client.clientsSeq"
          :value="client.clientsSeq">
        </el-option>
      </el-select>
      <el-input
        placeholder="Type something"
        prefix-icon="el-icon-search"
        v-model="searchName"
        style="margin-top:10px;"
        clearable
        >
      </el-input>
      <el-button
        type="primary"
        style="width:20%; margin-bottom:30px; margin-top:10px;"
        @click.native.prevent="checkSaerch"
      >
        {{ $t('route.search') }}
      </el-button>
      <el-button
        type="info"
        style="width:20%; margin-bottom:30px; margin-top:10px;"
        @click.native.prevent="resetList"
      >
        {{ $t('route.reset') }}
      </el-button>
    </div>
    <el-table
      :data="clientsData"
      style="width: 100%">
      <el-table-column
        label=""
        width="180">
        <el-radio-group v-model="radio">
          <el-radio prop="clientsSeq"></el-radio>
        </el-radio-group>
      </el-table-column>
      <el-table-column
        prop="clientsSeq"
        label="ID"
        width="180">
      </el-table-column>
      <el-table-column
        prop="clientsName"
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
import { infoClient } from '@/api/client'



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
  
  radio = ''

  pageNo = 0
  TargetClientSeq = ''
  searchName = ''

  
  private clientsData = [{}]

  created() {
    this.getList()
  }

  private async getList() {
    const { data } = await infoClient({})
    this.clientsData = data.clients
  }

  private async resetList() {
    const { data } = await infoClient({})
    this.clientsData = data.clients
    this.TargetClientSeq = ""
    this.searchName = ""
  }

  private async checkSaerch(){
    if(this.TargetClientSeq != '' && this.searchName == ''){
      const { data } = await infoClient({clientsSeq : this.TargetClientSeq})
      this.clientsData = data.clients
    } else if (this.TargetClientSeq == '' && this.searchName != ''){
      const { data } = await infoClient({clientsName : this.searchName})
      this.clientsData = data.clients
    }else if (this.TargetClientSeq != '' && this.searchName != ''){
      const { data } = await infoClient({clientsName : this.searchName, clientsSeq : this.TargetClientSeq})
      this.clientsData = data.clients
    }
  }

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