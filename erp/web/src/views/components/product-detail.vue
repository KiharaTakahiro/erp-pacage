<template>
<div>
  <div class="app-container">
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
    <el-form-item
      label="個数"
      prop="quantity"
      :rules="[
          { required: true, message: '個数を入力してください', trigger: 'change' }
        ]"
    >
      <el-input-number v-model="quantity" @change="quantitySubmit" :min="1" :max="10"></el-input-number>
      
    </el-form-item>
  </div>
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


  @Prop({ default: '' })
  productSeq!: string;
  @Prop()
  quantity!: Number;

  @Emit('productSeqSubmit')
  productSubmit() {
    return this.productSeq
  }
  @Emit('quantitySubmit')
  quantitySubmit() {
    return this.quantity
  }

  created() {
    this.getList()
  }

  private async getList() {
    const { data } = await pullDownProduct()
    this.products = data.product
  }


}



</script>

<style lang="scss" scoped>

</style>