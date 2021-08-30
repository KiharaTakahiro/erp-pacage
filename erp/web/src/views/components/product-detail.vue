<template>
  <div class="app-container">
    <el-row>
      <el-col :span="8">
        <el-form-item
        label="商品"
        prop="productSeq"
        :rules="[
            { required: true, message: '商品を選択してください', trigger: 'change' }
          ]"
        >
          <el-select v-model="productSeq" filterable clearable v-on:change="productSubmit" placeholder="商品">
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
        <el-input-number v-model="count" @change="quantitySubmit" :min="1" :max="10"></el-input-number>
      </el-col>
      <el-col :span="6">
        <el-input v-model="price"></el-input>
      </el-col>
    </el-row>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop, Emit} from 'vue-property-decorator'
import { pullDownProduct, getProduct } from '@/api/product'
import productDetail from '@/views/components/product-detail.vue'
@Component({
  name: 'productsPullDown',
  components: {
    
  }
})
export default class extends Vue {

  products = [{}]
  productPrice = 0
  price = 0
  count = 1
  taxType = ''


  @Prop({ default: '' })
  productSeq!: string;
  @Prop()
  quantity!: Number;

  @Emit('productSeqSubmit')
  productSubmit() {
    this.getProductDetail(this.productSeq)
    return this.productSeq
  }
  @Emit('quantitySubmit')
  quantitySubmit() {
    this.getTotalPrice()
    return this.quantity
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

  private getTotalPrice(){
    this.price = this.productPrice * this.count
  }


}



</script>

<style lang="scss" scoped>
.container{
  display: inline-flex;
}
</style>