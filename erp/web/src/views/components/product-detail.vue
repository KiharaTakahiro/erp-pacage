<template>
  <div class="app-container">
    <el-card class="box-card">
      <el-row>
        <el-col :span="6">
          <el-form-item label="商品名">
            <el-select
              :value="productSeqVal"
              @input="changeProduct"
              filterable
              clearable
              placeholder="商品"
              style="width:90%;"
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
          <el-form-item label="単価：￥">
            {{ price.toLocaleString() }}
          </el-form-item>
        </el-col>

        <el-col :span="5">
          <el-form-item label="個数">
            <el-input-number
              v-model="countVal"
              :step="1"
              :min="0"
              :max="100"
              style="width:90%;"
            />
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <money
            :label="label"
            :priceValue.sync="discountVal"
            style="width:90%;"
          />
        </el-col>
        <el-col :span="3">
          <el-form-item label="小計：￥">
            {{ subTotalValue.toLocaleString() }}
          </el-form-item>
        </el-col>
        <el-col :span="2">
          <div class="complete-btn right">
            <el-button type="danger" icon="el-icon-delete" @click.native.prevent="minusBtnClick" circle />
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="10">
          <date-form :date.sync="dateValue" label="配送日" />
        </el-col>
        <el-col :span="10">
          <delivery-status
            :status.sync="statusVal"/>
        </el-col>
        
      </el-row>
    </el-card>
  </div>
</template>

<script lang="ts">
import { Component, Vue, PropSync, Prop, Emit } from 'vue-property-decorator'
import { pullDownProduct, getProduct } from '@/api/product'
import DateForm from '@/views/components/date-form.vue'
import money from '@/views/components/money.vue'
import DeliveryStatus from '@/views/components/delivery-status.vue'
import { RecivedOrderModule } from '@/store/modules/recived-order'

@Component({
  name: 'productsPullDown',
  components: {
    DateForm,
    DeliveryStatus,
    money
  }
})
export default class extends Vue {
  //商品一覧
  products = [{}]
  // 配送状況一覧
  private RecivedOrderSatsus = []

  // 単価
  price = 0

  //値引のラベル
  label = '値引'

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
  public async getProductDetail(productSeq: any) {
    let { data } = await getProduct({ productSeq: productSeq })
    this.price = data.unitPrice
  }

  // 商品情報変更時の処理
  changeProduct(value: any) {
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
    /**
     * 編集画面の際金額が表示されないので
     * 該当のdetailKeyの商品Seqを確認
     * 値が入っていたら金額の取得のAPIをたたく。
     */
    const productSeq = RecivedOrderModule.details[this.detailKey].productSeq
    if(productSeq != '') {
      this.getProductDetail(productSeq)
    }
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

.right {
  float: right;
}
</style>
