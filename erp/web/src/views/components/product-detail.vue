<template>
  <div class="app-container">
    <el-row>
      <el-col :span="4">
        <el-form-item label="商品名">
          <el-select
            v-model="productSeqVal"
            filterable
            clearable
            placeholder="商品"
          >
            <el-option
              v-for="product in products"
              :key="product.productSeq"
              :label="product.productName"
              :value="product.productSeq"
            >
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="3">
        <el-form-item label="商品単価：￥">
          {{ price }}
        </el-form-item>
      </el-col>

      <el-col :span="4">
        <el-form-item label="個数">
          <el-input-number
            v-model="countVal"
            :step="1"
            :min="0"
            :max="100"
            style="width:60%;"
          />
        </el-form-item>
      </el-col>
      <el-col :span="2">
        <el-form-item label="値引">
          <el-input v-model="discountVal" style="width:50%;" />
        </el-form-item>
      </el-col>
      <el-col :span="4">
        <date-form :date.sync="dateValue" label="配送日" />
        <!-- <el-form-item
        label="配送日"
        >
      <el-date-picker
      v-model="dateVal"
      type="date"
      placeholder="配送日"
      style="width:60%;">
    </el-date-picker>
        </el-form-item> -->
      </el-col>
      <el-col :span="4">
        <el-form-item label="配送状況">
          <el-select
            v-model="statusVal"
            filterable
            clearable
            placeholder="配送状況"
          >
            <el-option
              v-for="status in RecivedOrderSatsus"
              :key="status.key"
              :label="status.value"
              :value="status.key"
            >
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
  </div>
</template>

<script lang="ts">
import { Component, Vue, PropSync, Prop, Emit } from 'vue-property-decorator'
import { pullDownProduct, getProduct } from '@/api/product'
import DateForm from '@/views/components/date-form.vue'
import { getCode } from '@/api/system'

@Component({
  name: 'productsPullDown',
  components: {
    DateForm
  }
})
export default class extends Vue {
  //商品一覧
  products = [{}]
  private RecivedOrderSatsus = []

  // 単価
  price = 0

  // 商品SEQ
  @PropSync('productSeq', { type: String }) productSeqVal!: string
  // 個数
  @PropSync('quantity', { type: String }) countVal!: string
  // 値下げ
  @PropSync('discount', { type: String }) discountVal!: number
  // 配送状況
  @PropSync('status', { type: String }) statusVal!: string
  // 配送日
  @PropSync('date', { type: String }) dateValue!: string

  // 商品の情報問合せ
  private async getProductDetail(productSeq: any) {
    let { data } = await getProduct({ productSeq: productSeq })
    this.price = data.unitPrice
  }

  // 作成時
  created() {
    this.getList()
    // HACK: ここはプルダウンをコンポーネント化したほうが良いが現時点ではこのまま
    this.getCode()
  }

  private async getCode() {
    const { data } = await getCode({ codeType: 'RacivedOrderStatus' })
    this.RecivedOrderSatsus = data.codes
  }

  // 商品プルダウン
  private async getList() {
    const { data } = await pullDownProduct()
    this.products = data.product
  }

}
</script>

<style lang="scss" scoped>
.container {
  display: inline-flex;
}
</style>
