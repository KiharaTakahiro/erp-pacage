<template>
  <div class="app-container">
    <el-row>
      <el-col :span="4">
        <el-form-item
        label="商品名"
        prop="productSeq"
        >
          <el-select v-model="productSeqVal" filterable clearable placeholder="商品">
            <el-option
              v-for="product in products"
              :key="product.productSeq"
              :label="product.productName"
              :value="product.productSeq">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="3">
        <el-form-item
        label="商品単価：￥"
        >
        {{price}}
        </el-form-item>
      </el-col>
        
      <el-col :span="4">
        <el-form-item
        label="個数"
        >
        <el-input-number v-model="countVal" :step="1" :min="0" :max="100" style="width:60%;"/>
        </el-form-item>
      </el-col>
      <el-col :span="2">
        <el-form-item
        label="値引"
        >
        <el-input v-model="discountVal" style="width:50%;"/>
        </el-form-item>
      </el-col>
      <el-col :span="3">
        <el-form-item
        label="配送日"
        >
      <el-date-picker
      v-model="dateVal"
      type="date"
      placeholder="配送日"
      style="width:60%;">
    </el-date-picker>
        </el-form-item>
      </el-col>
      <el-col :span="4">
        <el-form-item
        label="配送状況"
        >
          <el-select v-model="statusVal" filterable clearable placeholder="配送状況">
            <el-option
              v-for="status in RecivedOrderSatsus"
              :key="status.key"
              :label="status.value"
              :value="status.key">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop, Emit} from 'vue-property-decorator'
import { pullDownProduct, getProduct } from '@/api/product'
import { RecievedOrderModule } from '@/store/modules/recieved-order'



@Component({
  name: 'productsPullDown',
  components: {
    
  }
})

export default class extends Vue {

  //商品一覧
  products = [{}]
  //計算用の商品価格
  productPrice: bigint = 0n
  //税区分
  taxType = ''
  // 商品単価
  price = 0

  //商品Seq
  @Prop({ default: '' })
  productSeq!: string;
  //個数
  @Prop()
  quantity!: number;
  //値段
  @Prop()
  discount!: number;
  //配送状況
  @Prop()
  status!: number;
  @Prop({ default:'' })
  date!: string;

  //商品用のゲッター
  get productSeqVal(){
    return this.productSeq
  }
  //商品用セッター
  set productSeqVal(productSeq){
    this.productEmit(productSeq)
  }
  //商品用エミット
  @Emit('productSeqSubmit')
  productEmit(productSeq: any){
    this.getProductDetail(productSeq)
    return productSeq
  }

  //個数用ゲッター
  get countVal(){
    return this.quantity
  }
  //個数用セッター
  set countVal(quantity){
    this.quantityEmit(quantity)
  }
  //個数用エミット
  @Emit('quantitySubmit')
  quantityEmit(quantity: any){
    return quantity
  }
  //商品ゲッター
  get discountVal(){
    return this.discount
  }
  //商品セッター
  set discountVal(value){
    this.discountEmit(value)
  }
  //商品エミット
  @Emit('discountSubmit')
  discountEmit(discount: number){
    return discount
  }

  //配送状況用のゲッター
  get statusVal(){
    return this.status
  }
  //配送状況用セッター
  set statusVal(status){
    this.statusEmit(status)
  }
  //配送状況用エミット
  @Emit('statusSubmit')
  statusEmit(status: any){
    return status
  }
  
  //配送日用のゲッター
  get dateVal(){
    return this.date
  }
  //配送日況用セッター
  set dateVal(date){
    this.dateEmit(date)
  }
  //配送日用エミット
  @Emit('dateSubmit')
  dateEmit(date: any){
    return date
  }
  
  // 商品の情報問合せ
  private async getProductDetail(productSeq: any){
    let {data} = await getProduct({productSeq: productSeq})
    this.price = data.unitPrice
  }

  created() {
    this.getList()
  }
  // 商品プルダウン
  private async getList() {
    const { data } = await pullDownProduct()
    this.products = data.product
  }

  // FIXME: APIでプルダウンすること
  private RecivedOrderSatsus = [
    { key: 1, value: "受注準備中" }
    ]
}



</script>

<style lang="scss" scoped>
.container{
  display: inline-flex;
}
</style>