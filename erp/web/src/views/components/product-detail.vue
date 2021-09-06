<template>
  <div class="app-container">
    <el-row>
      <el-col :span="4">
        <el-form-item label="商品名">
          <el-select
            :value="productSeqVal"
            @input="changeProduct"
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
      <el-col :span="3">
        <el-form-item label="小計：￥">
          {{ subTotalValue }}
        </el-form-item>
      </el-col>

      <el-col :span="4">
        <date-form :date.sync="dateValue" label="配送日" />
      </el-col>
      <el-col :span="4">
        <delivery-status
          :status.sync="statusVal"/>
      </el-col>
      <el-col>
        <div class="complete-btn">
          <el-button
            type="info"
            icon="el-icon-minus"
            @click.native.prevent="minusBtnClick"
          />
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script lang="ts">
import { Component, Vue, PropSync, Prop, Emit } from 'vue-property-decorator'
import { pullDownProduct, getProduct } from '@/api/product'
import DateForm from '@/views/components/date-form.vue'
import DelivaryStatus from '@/views/components/delivery-status.vue'
import { getCode } from '@/api/system'
import DeliveryStatus from '@/views/components/delivery-status.vue'

@Component({
  name: 'productsPullDown',
  components: {
    DateForm,
    DeliveryStatus
  }
})
export default class extends Vue {
  //商品一覧
  products = [{}]
  private RecivedOrderSatsus = []

  // 単価
  price = 0

  // 詳細画面を判定するためのkey
  @Prop() detailKey!: number

  // マイナスボタンを押下時のイベント発火
  @Emit('clickMinusBtn')
  clickMinusBtn() {
    return this.detailKey
  }

  // NOTE: 合計計算に使えるかもしれないのでEmitしておく
  // 使えなかったらEmitは不要
  @Emit('subtotal')
  computeSubtotal(){
    return this.subTotalValue
  }

  // 商品SEQ
  @PropSync('productSeq', { type: String }) productSeqVal!: string
  // 個数
  @PropSync('quantity', { type: Number }) countVal!: number
  // 値下げ
  @PropSync('discount', { type: Number }) discountVal!: number
  // 配送状況
  @PropSync('status', { type: String }) statusVal!: string
  // 配送日
  @PropSync('date', { type: String }) dateValue!: string

  // 商品の情報問合せ
  private async getProductDetail(productSeq: any) {
    let { data } = await getProduct({ productSeq: productSeq })
    this.price = data.unitPrice
  }

  // 商品情報変更時の処理
  private changeProduct(value: any) {
    this.getProductDetail(value)
    this.productSeqVal = value
  }

  // 小計の計算
  get subTotalValue() {
    if (this.price == null || this.countVal == null || this.discountVal == null) {
      return 0
    }
      return (this.price * this.countVal) - this.discountVal
  }

  // 作成時
  created() {
    this.getList()
  }

  // 商品プルダウン
  private async getList() {
    const { data } = await pullDownProduct()
    this.products = data.product
  }

  minusBtnClick(){
    this.clickMinusBtn()
  }

}
</script>

<style lang="scss" scoped>
.container {
  display: inline-flex;
}
</style>
