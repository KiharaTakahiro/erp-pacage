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
      </el-table>
    </el-card>
  </div>
  
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { RecivedOrderListModule } from '@/store/modules/recived-order-list'
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
  }
  /**
   * テーブルデータ
   */
  get ordersData(){
    return RecivedOrderListModule.list
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
