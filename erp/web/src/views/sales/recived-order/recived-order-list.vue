<template>
  <div class="app-container">
    <el-card class="box-card">
      <h5>検索フォーム</h5>
      <div class="border">
        <el-form
          autocomplete="on"
          label-position="left"
          label-width="80px"
        >
          <clients-pull-down
          />
          <company-pull-down
          />
          <date-form
          />
          <date-form
          />
          <money
          style="width:70%;"
          />
          <money
          style="width:70%;"
          />
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
    </el-card>
  </div>

  
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { RecivedOrderListModule } from '@/store/modules/recived-order-list'
import clientsPullDown from '@/views/components/clients-pulldown.vue'
import companyPullDown from '@/views/components/company-pulldown.vue'
import DateForm from '@/views/components/date-form.vue'
import money from '@/views/components/money.vue'
import '@/assets/custom-theme/index.css'

@Component({
  name: 'RecievedOrder',
  components: {
    clientsPullDown,
    companyPullDown,
    DateForm,
    money
  }
})
export default class extends Vue {
  /**
   * 作成時
   */
  created() {
    this.getList()
  }
  // ページング条件
  pageNo = 1
  
  /**
   * ストアが更新されたら件数を算出
   */
  get totalItemsNum() {
    return RecivedOrderListModule.totalItem
  }
  /**
   * 検索フォーム用
   */
  targetRecivedOrderSeq = ''
  targetClientSeq = ''
  targetCompanySeq = ''
  targetDepartmentSeq = ''
  targetQuotationSeq = ''
  fromDate = ''
  toDate = ''
  fromTotal = 0
  toTotal = 0
  fromTax = 0
  toTax = 0
  /**
   * 一覧のリスト作成
   */
  private async getList(){
    const serchData = {
      pageNo: this.pageNo - 1,
      recivedOrderSeq: this.targetRecivedOrderSeq === "" ? null : this.targetRecivedOrderSeq,
      clientsSeq: this.targetClientSeq === '' ? null : this.targetClientSeq,
      companySeq: this.targetCompanySeq === '' ? null : this.targetCompanySeq,
      departmentSeq: this.targetDepartmentSeq === '' ? null : this.targetDepartmentSeq,
      fromDate: this.fromDate === '' ? null : this.fromDate,
      toDate: this.toDate === '' ? null : this.toDate,
      fromTotal: this.fromTotal === 0 ? null : this.fromTotal,
      toTotal: this.toTotal === 0 ? null : this.toTotal,
      fromTax: this.fromTotal === 0 ? null : this.fromTotal,
      toTax: this.toTax === 0 ? null : this.toTax
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

  /**
   * チェックボックスのカラムの値
   */
  checkNo(value: any){
    console.log(value)
  }
}
</script>

<style lang="scss" scoped>
.field-label {
  vertical-align: middle;
}

.box-card {
  width: 100%x;
  max-width: 100%;
  margin: 20px auto;
}

.block {
  padding: 30px 24px;
}

.tag-item {
  margin-right: 15px;
}

.page {
  margin-top: 1em;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
