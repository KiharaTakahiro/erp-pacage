<template>
  <div class="app-container">
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
      <el-table-column prop="clientsSeq" label="取引先" width="180"></el-table-column>
      <el-table-column prop="recivedOrderDate" label="受注日" width="180"></el-table-column>
      <el-table-column prop="total" label="合計金額" width="180"></el-table-column>
      <el-table-column prop="tax" label="消費税" width="180"></el-table-column>
      </el-table>
    </el-card>
  </div>
  
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { RecivedOrderListModule } from '@/store/modules/recived-order-list'
import { getClient } from '@/api/client'
import '@/assets/custom-theme/index.css'

@Component({
  name: 'RecievedOrder'
})
export default class extends Vue {
  /**
   * 作成時
   */
  created() {
    this.getList()
  }

  /**
   * 一覧のリスト作成
   */
  private async getList(){
    const serchData = {

      }
    await RecivedOrderListModule.RecivedOrderList(serchData)
    // 変換
    for(var i in RecivedOrderListModule.list){
      var order = RecivedOrderListModule.list[i]
      var clientsSeq = order.clientsSeq
      order.clientsSeq = await this.getClientsName(clientsSeq)
      console.log(order.clientsSeq)
      order.total = order.total.toLocaleString()
      order.tax = order.tax.toLocaleString()
    }
  }
  /**
   * テーブルデータ
   */
  get ordersData(){
    
    return RecivedOrderListModule.list
  }

  private async getClientsName(clientsSeq: any){
    const {data} = await getClient({clientsSeq: clientsSeq})
    return data.clientsName
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
</style>
