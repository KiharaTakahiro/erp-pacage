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
        <el-input-number v-model="countVal" :step="1" :min="0" :max="100" style="width:80%;"/>
        </el-form-item>
      </el-col>
      <el-col :span="4">
        <el-form-item
        label="値引"
        >
        <el-input v-model="priceVal" style="width:80%;"/>
        </el-form-item>
      </el-col>
      <el-col :span="6">
        <el-form-item
        label="配送状況"
        >
          <el-select v-model="satsus" filterable clearable placeholder="商品">
            <el-option
              v-for="status in RecivedOrderSatsus"
              :key="status.key"
              :label="status.value"
              :value="status.key">
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="2">
        <el-button 
          type="info" 
          icon="el-icon-plus"
          @click.native.prevent="jsonCommit"/>
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
  price!: number;

  @Prop({default: 1})
  status!: number;

  //商品用のゲッター
  get productSeqVal(){
    return this.productSeq
  }
  //商品用セッター
  set productSeqVal(productSeq){
    // this.getProductDetail(productSeq)
    this.productEmit(productSeq)
  }
  //商品用エミット
  @Emit('productSeqSubmit')
  productEmit(productSeq: any){
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
  get priceVal(){
    return this.price
  }
  //商品セッター
  set priceVal(value){
    this.priceEmit(value)
  }
  //商品エミット
  @Emit('priceSubmit')
  priceEmit(price: number){
    return price
  }

  created() {
    this.getList()
  }

  private async getList() {
    const { data } = await pullDownProduct()
    this.products = data.product
  }

  private RecivedOrderSatsus = [
    { key: 1, value: "受注準備中" }
    ]

  
  // detail  ={
  //   deriveryDate: string;
  //   discount: number;
  //   lotSeq: string;
  //   productSeq: string;
  //   quantity: number;
  //   status: string;
  //   }


  // jsonCommit(){
  //   RecievedOrderModule.pushDetail(this.detail)
  //   console.log(RecievedOrderModule.details)
  // }


}



</script>

<style lang="scss" scoped>
.container{
  display: inline-flex;
}
</style>