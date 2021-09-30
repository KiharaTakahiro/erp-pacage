<template>
  <div class="app-container">
    <el-card class="box-card">
      <h5>検索フォーム</h5>
      <div class="border">
        <el-form
          autocomplete="on"
          label-position="left"
          label-width="100px"
          :model="target"
        >
          <id-search
            :targetId.sync="target.recivedOrderSeq"
            :label="idLabel"
          />
          <clients-pull-down
          :clientsSeq.sync="target.clientSeq"
          />
          <company-pull-down
          :companySeq.sync="target.companySeq"
          :departmentSeq.sync="target.departmentSeq"
          @resetDepart="resetDepart"
          />
          <el-row>
            <el-col :span="10">
              <date-form
              style="width:90%;"
              :label="deriFrom"
              :date="target.fromDate"
              />
            </el-col>
            <el-col :span="10">
              <date-form
              style="width:90%;"
              :label="deriTo"
              :date="target.toDate"
              />
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="5">
              <money
              style="width:80%;"
              :label="fromTotal"
              :priceValue.sync="target.fromTotal"
              />
            </el-col>
            <el-col :span="5">
              <money
              style="width:80%;"
              :label="toTotal"
              :priceValue.sync="target.toTotal"
              />
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="5">
              <money
              style="width:80%;"
              :label="fromTax"
              :priceValue.sync="target.fromTax"
              />
            </el-col>
            <el-col :span="5">
              <money
              style="width:80%;"
              :label="toTax"
              :priceValue="target.toTax"
              />
            </el-col>
          </el-row>
        </el-form>
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
    <div>{{ $t('route.recivedOrder') }}</div>
    <el-card class="box-card">
      <el-table
        ref="RecivedOrderTable"
        :data="ordersData"
        @selection-change="checkNo"
        style="width: 100%"
      >
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column prop="recivedOrderSeq" label="ID" width="180"></el-table-column>
        <el-table-column prop="clientsName" label="取引先" width="180"></el-table-column>
        <el-table-column prop="recivedOrderDate" label="受注日" width="180"></el-table-column>
        <el-table-column prop="total" label="合計金額" width="180"></el-table-column>
        <el-table-column prop="tax" label="消費税" width="180"></el-table-column>
        <el-table-column prop="companyName" label="受注会社" width="180"></el-table-column>
        <el-table-column prop="departmentName" label="受注部署" width="180"></el-table-column>
      </el-table>
      <div class="page">
        <el-pagination
          background
          :page-size="15"
          @current-change="handleCurrentChange"
          layout="prev, pager, next"
          :total="totalItemsNum"
          :current-page.sync="pageNo"
        >
        </el-pagination>
      </div>
      <div class="left">
        <back-btn />
      </div>
      <div class="right">
        <el-button
          type="primary"
          style="width:45%; margin-bottom:30px; margin-top:30px; margin-left:50%"
          @click.native.prevent="editBtn"
        >
          {{ $t('route.edit') }}
        </el-button>
      </div>
    </el-card>
  </div>
</template>

<script lang="ts">
import { RecivedOrderModule } from '@/store/modules/recived-order'
import { Component, Vue } from 'vue-property-decorator'
import { RecivedOrderListModule } from '@/store/modules/recived-order-list'
import clientsPullDown from '@/views/components/clients-pulldown.vue'
import companyPullDown from '@/views/components/company-pulldown.vue'
import DateForm from '@/views/components/date-form.vue'
import money from '@/views/components/money.vue'
import idSearch from '@/views/components/id-search.vue'
import backBtn from '@/views/components/back-button.vue'
import '@/assets/custom-theme/index.css'

@Component({
  name: 'RecivedOrder',
  components: {
    clientsPullDown,
    companyPullDown,
    DateForm,
    money,
    idSearch,
    backBtn
  }
})
export default class extends Vue {
  /**
   * 作成時
   */
  created() {
    this.getList()
  }

  recivedOrder = {
    recivedOrderSeq: ''
  }

  // ページング条件
  pageNo = 1

  // チェックのバリデーション用の数字
  checkLength = 0

  //
  idLabel = '受注ID'

  // 日付のラベル
  deriFrom = '配送日から'
  deriTo = '配送日まで'

  // 合計金額ラベル
  fromTotal = '合計金額から'
  toTotal = '合計金額まで'

  // 税金ラベル
  fromTax = '税金計から'
  toTax = '税金計まで'
  
  /**
   * ストアが更新されたら件数を算出
   */
  get totalItemsNum() {
    return RecivedOrderListModule.totalItem
  }
  /**
   * 検索フォーム用
   */
  private target = {
    recivedOrderSeq: '',
    clientSeq: '',
    companySeq: '',
    departmentSeq: '',
    quotationSeq: '',
    fromDate: '',
    toDate: '',
    fromTotal: 0,
    toTotal: 0,
    fromTax: 0,
    toTax: 0
  }

  //部署リセット
  resetDepart(){
    this.target.departmentSeq = ''
  }

  /**
   * 一覧のリスト作成
   */
  private async getList(){
    const serchData = {
      pageNo: this.pageNo - 1,
      recivedOrderSeq: this.target.recivedOrderSeq === "" ? null : this.target.recivedOrderSeq,
      clientsSeq: this.target.clientSeq === '' ? null : this.target.clientSeq,
      companySeq: this.target.companySeq === '' ? null : this.target.companySeq,
      departmentSeq: this.target.departmentSeq === '' ? null : this.target.departmentSeq,
      fromDate: this.target.fromDate === '' ? null : this.target.fromDate,
      toDate: this.target.toDate === '' ? null : this.target.toDate,
      fromTotal: this.target.fromTotal === 0 ? null : this.target.fromTotal,
      toTotal: this.target.toTotal === 0 ? null : this.target.toTotal,
      fromTax: this.target.fromTotal === 0 ? null : this.target.fromTotal,
      toTax: this.target.toTax === 0 ? null : this.target.toTax
      }
    await RecivedOrderListModule.RecivedOrderList(serchData)
    
  }
  /**
   * テーブルデータ
   */
  get ordersData(){
    return RecivedOrderListModule.list
  }
  
  /**
   * ページが変更される時の処理
   */
  handleCurrentChange(val: any) {
    this.pageNo = val
    this.getList()
  }

  // 検索ボタン
  private searchBtn(){
    this.getList()
  }

  // リセットボタン
  private resetBtn(){
    this.pageNo = 1
    this.target.recivedOrderSeq = ''
    this.target.clientSeq = ''
    this.target.companySeq = ''
    this.target.departmentSeq = ''
    this.target.fromDate = ''
    this.target.toDate = ''
    this.target.fromTotal = 0
    this.target.toTotal = 0
    this.target.fromTax = 0
    this.target.toTax = 0

    this.getList()
  }

  /**
   * チェックボックスのカラムの値
   */
  checkNo(value: any){
    this.recivedOrder.recivedOrderSeq = value[0]['recivedOrderSeq']
    this.checkLength = value.length
  }

  editBtn() {
    if (this.checkLength === 0) {
      this.$message({
        message: this.$t('recivedOrder.check0').toString(),
        type: 'error'
      })
      return false
    } else if (this.checkLength >= 2) {
      this.$message({
        message: this.$t('recivedOrder.check2').toString(),
        type: 'error'
      })
      return false
    }
    RecivedOrderModule.GetRecivedOrder(this.recivedOrder)
    this.$router
      .push({
        path: 'edit-recived-order'
      })
      .catch(err => {
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
  width: 100%;
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
  width: 25%;
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

.box-card {
  width: 100%;
  padding: 15px;
}
</style>
