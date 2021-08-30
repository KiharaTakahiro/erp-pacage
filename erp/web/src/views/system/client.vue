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
          v-model="search
          Name"
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
          @click.native.prevent="resetBtn"
        >
          {{ $t('route.reset') }}
        </el-button>
        <el-button
          size="small"
          type="primary"
          style="width:45%; margin-top:10px;"
          @click.native.prevent="searchBtn"
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
        @selection-change="checkNo"
        style="width: 100%">
        <el-table-column
          type="selection"
          width="55">
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
      <div class="page">
        <el-pagination
            background
            :page-size=15
            @current-change="handleCurrentChange"
            layout="prev, pager, next"
            :total="totalItemsNum"
            :current-page.sync="pageNo"
            >
        </el-pagination>
      </div>
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
import { ClientModule } from '@/store/modules/client'
import { ClientListModule } from '@/store/modules/client_list'
import '@/assets/custom-theme/index.css'
import backBtn from "@/views/components/back-button.vue"

@Component({
  name: 'Client',
  components :{
    backBtn
  }
})
export default class extends Vue {

  // 編集用条件
  client = {
    id: ''
  }
  // チェックのバリデーション用の数字
  checkLength = 0

  // ページング条件
  pageNo = 1

  // 検索条件
  targetClientSeq = ''
  searchName = ''
  
  /**
   * 作成時
   */
  created() {
    this.getList()
  }

  /**
   * ストアが更新されたら件数を算出
   */
  get totalItemsNum() {
    return ClientListModule.totalItem
  }

  /**
   * ストアが更新されたらクライアントを算出
   */
  get clientsData () {
    return ClientListModule.list
  }

  /**
   * APIへリスト取得処理
   */
  private async getList() {
    
    // 検索パラメタを生成する
    let searchData = { 
      pageNo: this.pageNo - 1, 
      clientsSeq : this.targetClientSeq == '' ? null : this.targetClientSeq,
      clientsName: this.searchName == '' ? null : this.searchName
    }

    // APIの取得結果をもとにModelを更新する
    await ClientListModule.ClientList(searchData)
  }

  /**
   * 画面時のボタンセレクト条件
   */
  // TODO: 適切な名前に変更する
  private checkNo(val : any){
    this.client.id = val[0]['clientsSeq']
    this.checkLength = val.length
  }

  /**
   * リセットボタン押下時の処理
   */
  resetBtn() {
    this.targetClientSeq = ""
    this.searchName = ""
    this.pageNo = 1
    this.getList()
  }

  /**
   * 検索ボタン押下時の処理
   */
  searchBtn() {
    this.pageNo = 1
    this.getList()
  }

  /**
   * 新規作成ボタン押下時の処理
   */
  createClientBtn() {
    this.$router.push({
    path:'save-client'
    }).catch(err => {
      console.warn(err)
    })
  }

  /**
   * 編集ボタン押下時の処理
   */
  editClientBtn() {
    if(this.checkLength == 0){
      this.$message({
      message: this.$t('client.check0').toString(),
      type: 'error'
      })
      return false
    } else if (this.checkLength >= 2){
      this.$message({
      message: this.$t('client.check2').toString(),
      type: 'error'
      })
      return false
    }
    ClientModule.EditClient(this.client)
    this.$router.push({
    path:'edit-client'
    }).catch(err => {
      console.warn(err)
    })
  }

  /**
   * ページが変更される時の処理
   */
  handleCurrentChange(val: any) {
    this.pageNo = val
    this.getList()
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

.page {
  margin-top: 1em;
  display: flex;
  justify-content: center;
  align-items: center;
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
