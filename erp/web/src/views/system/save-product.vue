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
        :productName.sync="product.productName"/>
      <tax-type-pulldown
        :taxTypeValue.sync="product.taxType"/>
      <purchase-unit-price
        :purchaseUnitPrice.sync="product.purchaseUnitPrice"/>
      <unit-price
        :unitPrice.sync="product.unitPrice"/>
      <div class="complete-btn">
        <el-button
            type="primary"
            style="width:100%;"
            @click.native.prevent="createProduct">
    
            {{ $t('product.complete') }}
        </el-button>
      </div>
    </el-form>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { Form as ElForm } from 'element-ui'
import '@/assets/custom-theme/index.css'
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
  product = {
    productName: ProductModule.productName,
    taxType: ProductModule.taxType,
    purchaseUnitPrice: ProductModule.purchaseUnitPrice,
    unitPrice: ProductModule.unitPrice
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