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
        :TaxTypePulldown="product.taxType"
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
import productName  from "@/views/components/product-name.vue"
import TaxTypePulldown from '../components/tax-type-pulldown.vue'


@Component({
  name: 'product-save',
  components :{
    productName,
    TaxTypePulldown
  }

})

export default class extends Vue {
  private product = {
    productName:'',
    taxType:''
  }
  private productNameRecieve(productName: any): void{
    this.product.productName = productName
  }
  private taxTypeRecieve(productName: any): void{
    this.product.taxType = productName
  }
  private createProduct(){
    console.log(this.product.productName)
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
