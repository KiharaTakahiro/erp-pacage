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
      <purchase-unit-price
        :purchaseUnitPrice="product.purchaseUnitPrice"
        @purchaseUnitPriceSubmit="purchaseUnitPriceRecieve"
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
import '@/assets/custom-theme/index.css'
import { component } from 'node_modules/vue/types/umd'
import productName  from '@/views/components/product-name.vue'
import taxTypePulldown from '../components/tax-type-pulldown.vue'
import purchaseUnitPrice from '@/views/components/purchase-unit-price.vue'
import unitPrice from '@/views/components/unit-price.vue'
import { ProductModule } from '@/store/modules/product'


@Component({
  name: 'product-save',
  components :{
    productName,
    taxTypePulldown,
    purchaseUnitPrice,
    unitPrice
  }

})

export default class extends Vue {
  private product = {
    productName:'',
    taxType:'',
    purchaseUnitPrice:'',
    unitPrice:''
  }
  private productNameRecieve(productName: any): void{
    this.product.productName = productName
  }
  private taxTypePulldownRecieve(taxType: any): void{
    this.product.taxType = taxType
  }
  private purchaseUnitPriceRecieve(purchaseUnitPrice: any): void{
    this.product.purchaseUnitPrice = purchaseUnitPrice
  }
    private unitPriceRecieve(unitPrice: any): void{
    this.product.unitPrice = unitPrice
  }
  private createProduct(){    
    (this.$refs.product as ElForm).validate(async(valid: boolean) => {
    if(valid){
      await ProductModule.CreateProduct(this.product)
      this.$router.push({
          path: 'product'
        }).catch(err => {
          console.warn(err)
        })
      this.$message({
      message: this.$t('components.createProduct').toString(),
      type: 'success'
    })
      }else {
        this.$message({
        message: this.$t('components.validation').toString(),
        type: 'error'
        })
        return false
      }
    })

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