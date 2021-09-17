<template>
  <div class="app-container">
    <div>{{ $t('route.warehouseList') }}</div>
    <el-card class="box-card">
      <h5>検索フォーム</h5>
      <div class="border">
        <el-form
          :model="warehouse"
          ref="warehouse"
          autocomplete="on"
          label-position="left"
          label-width="90px"
        >
          <id-search
          style=" width:50%;"
          label="倉庫Seq"
          :targetId.sync="targetWarehouseSeq" />

          <warehouse-name 
          style="width:50%;"
          :warehouseName.sync="searchName" />
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
    <el-card class="box-card">
      <h5>倉庫一覧</h5>
      <el-table
        ref="warehouseTable"
        :data="warehouseData"
        @selection-change="testLog"
        style="width: 100%"
      >
        <!-- <el-table-column
          label=""
          type="selection"
          width="180">
        </el-table-column> -->
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column prop="warehouseSeq" label="ID" width="180">
        </el-table-column>
        <el-table-column prop="warehouseName" label="名前"> </el-table-column>
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
          style="width:100%; margin-bottom:30px; margin-top:30px; "
          @click.native.prevent="editWarehouseBtn"
        >
          {{ $t('route.edit') }}
        </el-button>
      </div>
    </el-card>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { WarehouseModule } from '@/store/modules/warehouse'
import '@/assets/custom-theme/index.css'
import backBtn from '@/views/components/back-button.vue'
import { getCode } from '@/api/system'
import warehouseName from '@/views/components/warehouse-name.vue'
import IdSearch from '@/views/components/id-search.vue'


@Component({
  name: 'Warehouse',
  components: {
    backBtn,
    warehouseName,
    IdSearch
  }
})
export default class extends Vue {
  warehouse = {
    id: ''
  }
  checkLength = 0

  // ページング条件
  pageNo = 1
  internalPage: any

  // 検索条件
  targetWarehouseSeq = ''
  searchName = ''
  searchUnitPriceFrom = ''
  searchUnitPriceTo = ''
  searchPurchaseUnitPriceFrom = ''
  searchPurchaseUnitPriceTo = ''
  searchTaxType = null

  created() {
    this.getList()
  }

  /**
   * ストアが更新されたら件数を算出
   */
  get totalItemsNum() {
    return WarehouseModule.totalItem
  }

  /**
   * ストアが更新されたらクライアントを算出
   */
  get warehouseData() {
    return WarehouseModule.list
  }
  /**
   * APIへリスト取得処理
   */
  private async getList() {
    // 検索パラメタを生成する
    let searchData = {
      pageNo: this.pageNo - 1,
      warehouseSeq: this.targetWarehouseSeq === '' ? null : this.targetWarehouseSeq,
      warehouseName: this.searchName === '' ? null : this.searchName,
      unitPriceFrom: this.searchUnitPriceFrom === '' ? null : this.searchUnitPriceFrom,
      unitPriceTo: this.searchUnitPriceTo === '' ? null : this.searchUnitPriceTo,
      purchaseUnitPriceFrom: this.searchPurchaseUnitPriceFrom === '' ? null : this.searchPurchaseUnitPriceFrom,
      purchaseUnitPriceTo: this.searchPurchaseUnitPriceTo === '' ? null : this.searchPurchaseUnitPriceTo,
      taxType: this.searchTaxType === '' ? null : this.searchTaxType
    }

    // APIの取得結果をもとにModelを更新する
    await WarehouseModule.WarehouseList(searchData)
  }

  /**
   * 画面時のボタンセレクト条件
   */
  // TODO: 適切な名前に変更する
  private testLog(val: any) {
    this.warehouse.id = val[0]['warehouseSeq']
    this.checkLength = val.length
  }

  /**
   * リセットボタン押下時の処理
   */
  resetBtn() {
    this.targetWarehouseSeq = ''
    this.searchName = ''
    this.searchUnitPriceFrom = ''
    this.searchUnitPriceTo = ''
    this.searchPurchaseUnitPriceFrom = ''
    this.searchPurchaseUnitPriceTo = ''
    this.searchTaxType = null
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

  editWarehouseBtn() {
    // ボタンが押されたときの処理
    if (this.checkLength === 0) {
      this.$message({
        message: this.$t('warehouse.check0').toString(),
        type: 'error'
      })
      return false
    } else if (this.checkLength >= 2) {
      this.$message({
        message: this.$t('warehouse.check2').toString(),
        type: 'error'
      })
      return false
    }
    WarehouseModule.EditWarehouse(this.warehouse)
    this.$router
      .push({
        path: 'edit-warehouse'
      })
      .catch(err => {
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
  width: 7%
}

.from-to {
  display: inline-block;
}

.box-card {
  width: 100%;
  padding: 15px;
}
</style>