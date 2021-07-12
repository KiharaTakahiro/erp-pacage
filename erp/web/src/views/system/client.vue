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
    <el-card class="box-card">
      <h5>検索フォーム</h5>
      <div class="border">
        <span class="input-label">ID:</span>
        <el-input
          placeholder=""
          v-model="targetClientSeq"
          style="margin-top:10px; width:5%; margin-right:20px;"
          clearable
          >
        </el-input>
        <span class="input-label">Name:</span>
        <el-input
          placeholder=""
          prefix-icon="el-icon-search"
          v-model="searchName"
          style="margin-top:10px; width:40%;"
          clearable
          >
        </el-input>
      </div>
      <div class="right">
        <el-button
          size="small"
          type="info"
          style="width:45%; margin-top:10px;"
          @click.native.prevent="resetList"
        >
          {{ $t('route.reset') }}
        </el-button>
        <el-button
          size="small"
          type="primary"
          style="width:45%; margin-top:10px;"
          @click.native.prevent="checkSaerch"
        >
          {{ $t('route.search') }}
        </el-button>
      </div>
    </el-card>
    <el-card class="box-card">
      <h5>取引先一覧</h5>
      <el-table
        ref="clientsTable"
        :data="clientsData"
        highlight-current-row
        @current-change="testLog"
        style="width: 100%">
        <!-- <el-table-column
          label=""
          type="selection"
          width="180">
        </el-table-column> -->
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
            style="width:100%; margin-bottom:30px; margin-top:30px; "
            @click.native.prevent="editClientBtn"
          >
            {{ $t('route.edit') }}
          </el-button>
        </div>
    </el-card>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
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
  
  radio = 2

  pageNo = 0
  targetClientSeq = ''
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
    this.targetClientSeq = ""
    this.searchName = ""
    console.log(this.radio)
  }

  private async checkSaerch(){
    if(this.targetClientSeq != '' && this.searchName == ''){
      const { data } = await infoClient({clientsSeq : this.targetClientSeq})
      this.clientsData = data.clients
    } else if (this.targetClientSeq == '' && this.searchName != ''){
      const { data } = await infoClient({clientsName : this.searchName})
      this.clientsData = data.clients
    }else if (this.targetClientSeq != '' && this.searchName != ''){
      const { data } = await infoClient({clientsName : this.searchName, clientsSeq : this.targetClientSeq})
      this.clientsData = data.clients
    }
  }

  private testLog(val : any){
    this.client.id = val.clientsSeq
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

.input-label {
  display: inline-block;
  width: 5%;
  }

.box-card {
  width: 100%;
  padding: 15px;
  }
</style>