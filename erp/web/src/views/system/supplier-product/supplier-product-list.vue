<template>
  <div class="app-container">
    <div>{{ $t('route.supplierProduct') }}</div>
    <el-card class="box-card">
      <h5>検索フォーム</h5>
      <div class="border">
        <id-search
          style=" width:50%;"
          label="商品Seq"
          :targetId.sync="targetProductSeq" />
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
          @click.native.prevent="checkSaerch"
        >
          {{ $t('route.search') }}
        </el-button>
      </div>
    </el-card>
    <el-card class="box-card">
      <h5>仕入商品一覧</h5>
      <el-table
        ref="supplierProductTable"
        :data="supplierProductData"
        @selection-change="testLog"
        style="width: 100%"
      >
        <!-- <el-table-column
          label=""
          type="selection"
          width="180">
        </el-table-column> -->
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column prop="supplierProductSeq" label="ID" width="180">
        </el-table-column>
        <el-table-column
          prop="supplierProductName"
          label="Supplier product name"
        >
        </el-table-column>
      </el-table>
      <div class="left">
        <back-btn />
      </div>
      <div class="right">
        <el-button
          type="primary"
          style="width:100%; margin-bottom:30px; margin-top:30px; "
          @click.native.prevent="checkSaerch"
        >
          {{ $t('route.edit') }}
        </el-button>
      </div>
    </el-card>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import '@/assets/custom-theme/index.css'
import backBtn from '@/views/components/back-button.vue'
import { SupplierProductModule } from '@/store/modules/supplier-product-list'

@Component({
  name: 'SupplierProduct',
  components: {
    backBtn
  }
})
export default class extends Vue {
  supplierProduct = {
    id: ''
  }

  checkLength = 0

  pageNo = 0
  targetSupplierProductSeq = ''
  searchName = ''

  created() {
    this.getList()
  }

  private async getList() {
    const searchData = {
      pageNo: this.pageNo - 1,
      supplierProductSeq:
        this.targetSupplierProductSeq === ''
          ? null
          : this.targetSupplierProductSeq,
      supplierProductName: this.searchName === '' ? null : this.searchName
    }
    SupplierProductModule.SupplierProductList(searchData)
  }

  private async resetBtn() {
    this.targetSupplierProductSeq = ''
    this.searchName = ''
    this.pageNo = 1
    this.getList()
  }

  private testLog(val: any) {
    this.supplierProduct.id = val[0]['supplierProductSeq']
    this.checkLength = val.length
  }

  createSupplierProductBtn() {
    // ボタンが押されたときの処理
    this.$router
      .push({
        path: 'save-supplier-product'
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
