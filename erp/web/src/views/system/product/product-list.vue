<template>
  <div class="app-container">
    <div>{{ $t('route.productList') }}</div>
    <el-card class="box-card">
      <h5>検索フォーム</h5>
      <div class="border">
        <el-form
          :model="product"
          ref="product"
          autocomplete="on"
          label-position="left"
          label-width="90px"
        >
          <product-seq 
          style="margin-top:10px; width:10%; margin-right:20px;"
          :productName.sync="targetProductSeq" />

          <product-name 
          style="width:50%;"
          :productName.sync="searchName" />

          <money
          label="定価FROM"
          style="width:30%;"
          :placeholder="$t('product.unitPriceFrom')"
          :priceValue.sync="searchUnitPriceFrom"
          />
          <money
          label="定価TO"
          style="width:30%;"
          :placeholder="$t('product.unitPriceTo')"
          :priceValue.sync="searchUnitPriceTo"
          />

          <money
          label="原価FROM"
          style="width:30%;"
          :placeholder="$t('product.unitPurchasePriceFrom')"
          :priceValue.sync="searchPurchaseUnitPriceFrom"
          />
          <money
          label="原価TO"
          style="width:30%;"
          :placeholder="$t('product.PurchaseunitPriceTo')"
          :priceValue.sync="searchPurchaseUnitPriceTo"
          />
          <tax-type-pulldown :taxTypeValue.sync="searchTaxType" />
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
      <h5>商品一覧</h5>
      <el-table
        ref="productTable"
        :data="productData"
        @selection-change="testLog"
        style="width: 100%"
      >
        <!-- <el-table-column
          label=""
          type="selection"
          width="180">
        </el-table-column> -->
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column prop="productSeq" label="ID" width="180">
        </el-table-column>
        <el-table-column prop="productName" label="名前"> </el-table-column>
        <el-table-column prop="purchaseUnitPrice" label="仕入れ価格">
        </el-table-column>
        <el-table-column prop="unitPrice" label="単価"> </el-table-column>
        <el-table-column prop="taxType" label="税区分"> </el-table-column>
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
          @click.native.prevent="editProductBtn"
        >
          {{ $t('route.edit') }}
        </el-button>
      </div>
    </el-card>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { ProductModule } from '@/store/modules/product'
import '@/assets/custom-theme/index.css'
import backBtn from '@/views/components/back-button.vue'
import { getCode } from '@/api/system'
import money from '@/views/components/money.vue'
import productSeq from '@/views/components/product-seq.vue'
import productName from '@/views/components/product-name.vue'
import taxTypePulldown from '@/views/components/tax-type-pulldown.vue'


@Component({
  name: 'Product',
  components: {
    backBtn,
    productSeq,
    productName,
    taxTypePulldown,
    money
  }
})
export default class extends Vue {
  product = {
    id: ''
  }
  checkLength = 0

  // ページング条件
  pageNo = 1
  internalPage: any

  // 検索条件
  targetProductSeq = ''
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
    return ProductModule.totalItem
  }

  /**
   * ストアが更新されたらクライアントを算出
   */
  get productData() {
    return ProductModule.list
  }
  /**
   * APIへリスト取得処理
   */
  private async getList() {
    // 検索パラメタを生成する
    let searchData = {
      pageNo: this.pageNo - 1,
      productSeq: this.targetProductSeq === '' ? null : this.targetProductSeq,
      productName: this.searchName === '' ? null : this.searchName,
      unitPriceFrom: this.searchUnitPriceFrom === '' ? null : this.searchUnitPriceFrom,
      unitPriceTo: this.searchUnitPriceTo === '' ? null : this.searchUnitPriceTo,
      purchaseunitPriceFrom: this.searchPurchaseUnitPriceFrom === '' ? null : this.searchPurchaseUnitPriceFrom,
      purchaseunitPriceTo: this.searchPurchaseUnitPriceTo === '' ? null : this.searchPurchaseUnitPriceTo,
      taxType: this.searchTaxType === '' ? null : this.searchTaxType
    }

    // APIの取得結果をもとにModelを更新する
    await ProductModule.ProductList(searchData)

    console.log(this.searchTaxType)
  }

  /**
   * 画面時のボタンセレクト条件
   */
  // TODO: 適切な名前に変更する
  private testLog(val: any) {
    this.product.id = val[0]['productSeq']
    this.checkLength = val.length
  }

  /**
   * リセットボタン押下時の処理
   */
  resetBtn() {
    this.targetProductSeq = ''
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

  createProductBtn() {
    this.$router
      .push({
        path: 'save-product'
      })
      .catch(err => {
        console.warn(err)
      })
  }

  editProductBtn() {
    // ボタンが押されたときの処理
    if (this.checkLength === 0) {
      this.$message({
        message: this.$t('product.check0').toString(),
        type: 'error'
      })
      return false
    } else if (this.checkLength >= 2) {
      this.$message({
        message: this.$t('product.check2').toString(),
        type: 'error'
      })
      return false
    }
    ProductModule.EditProduct(this.product)
    this.$router
      .push({
        path: 'edit-product'
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