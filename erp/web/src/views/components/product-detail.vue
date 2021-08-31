<template>
  <div class="app-container">
    <el-row>
      <el-col :span="8">
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
      <el-col :span="6">
        <el-input-number v-model="countVal" :step="1" :min="0" :max="100"></el-input-number>
      </el-col>
      <el-col :span="6">
        <el-input v-model="priceVal"></el-input>
      </el-col>
    </el-row>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop, Emit} from 'vue-property-decorator'
import { pullDownProduct, getProduct } from '@/api/product'
import productDetail from '@/views/components/product-detail.vue'
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
  num=0

  //商品Seq
  @Prop({ default: '' })
  productSeq!: string;
  //個数
  @Prop({ default: 0 })
  quantity!: number;
  //値段
  @Prop({ default: 0 })
  price!: string;

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
    return productSeq
  }

  get countVal(){
    return this.quantity
  }
  set countVal(quantity){
    this.qutantityEmit(quantity)
  }

  @Emit('quantitySubmit')
  qutantityEmit(quantity: any){
    return quantity
  }

  get priceVal(){
    return this.price
  }

  set priceVal(value){
    this.$emit('priceSubmit', value)
  }

  created() {
    this.getList()
  }

  private async getList() {
    const { data } = await pullDownProduct()
    this.products = data.product
  }

  private async getProductDetail(productSeq: any){
    let {data} = await getProduct({productSeq: productSeq})
    this.productPrice = data.unitPrice
    this.price = data.unitPrice
    this.taxType = data.taxType
  }

  // private getTotalPrice(){
  //   this.price = this.productPrice * this.quantity
  // }


}



</script>

<style lang="scss" scoped>
.container{
  display: inline-flex;
}
</style>