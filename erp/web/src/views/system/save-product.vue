<template>
  <div class="app-container">
    <div>{{ $t("route.newProduct") }}</div>
    <br>
    <br>
    <el-form
      ref="product"
      :model="product"
      autocomplete="on"
      label-position="left"
    >
      <product-name
        :productName="product.productName"
        @productNameSubmit="productNameRecieve"
        />
      <tax-type-pulldown
        :taxTypePulldown="product.taxType"
        @taxTypePulldownSubmit="taxTypePulldownRecieve"
        />
      <purchace-unit-price
        :purchaceUnitPrice="product.purchaceUnitPrice"
        @purchaceUnitPriceSubmit="purchaceUnitPriceRecieve"
        />
      <unit-price
        :unitPrice="product.unitPrice"
        @unitPriceSubmit="unitPriceRecieve"
        />
      <div class="complete-btn">
        <el-button
            type="primary"
            style="width:100%;"
            @click.native.prevent="createProduct"
          >
    
            {{ $t('product.complete') }}
        </el-button>
      </div>
    </el-form>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'
import { Form as ElForm, Input } from 'element-ui'
import { Dictionary } from 'vue-router/types/router'
import productName  from '@/views/components/product-name.vue'
import taxTypePulldown from '../components/tax-type-pulldown.vue'
import purchaceUnitPrice from '@/views/components/purchace-unit-price.vue'
import unitPrice from '@/views/components/unit-price.vue'

@Component({
  name: 'product-save',
  components :{
    productName,
    taxTypePulldown,
    purchaceUnitPrice,
    unitPrice
  }

})

export default class extends Vue {
  private product = {
    productName:'',
    taxType:'',
    purchaceUnitPrice:'',
    unitPrice:''
  }
  private productNameRecieve(productName: any): void{
    this.product.productName = productName
  }
  private taxTypePulldownRecieve(taxType: any): void{
    this.product.taxType = taxType
  }
  private purchaceUnitPriceRecieve(purchaceUnitPrice: any): void{
    this.product.purchaceUnitPrice = purchaceUnitPrice
  }
    private unitPriceRecieve(unitPrice: any): void{
    this.product.unitPrice = unitPrice
  }
  private createProduct(){
    console.log(this.product.productName)
    console.log(this.product.purchaceUnitPrice)
    console.log(this.product.taxType)
    console.log(this.product.unitPrice)
    
    // (this.$refs.user as ElForm).validate(async(valid: boolean) => {
    //   if(valid){

  }

}



</script>

<style lang="scss" scoped>
.field-label {
  vertical-align: middle;
}

.app-container{
  width: 50%;
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

.complete-btn{
  float: right;
}

</style>
