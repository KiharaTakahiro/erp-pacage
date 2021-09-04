<template>
  <div class="app-container">
    <div>{{ $t("route.editProduct") }}</div>
    <br>
    <br>
    <el-form
      ref="product"
      :model="product"
      autocomplete="on"
      label-position="left">
      <product-name
        :productName.sync="product.productName"/>
      <tax-type-pulldown
        :taxTypeValue.sync="product.taxType"/>
      <money
        label="仕入料金"
        prop="purchaseUnitPrice"
        :required="true"
        :placeholder="$t('product.purchaseUnitPrice')"
        :priceValue.sync="product.purchaseUnitPrice"
        />
      <money
        label="定価"
        prop="unitPrice"
        :required="true"
        :placeholder="$t('product.unitPrice')"
        :priceValue.sync="product.unitPrice"
        />
      <div class="complete-btn">
        <el-button
            type="primary"
            style="width:100%;"
            @click.native.prevent="updateProduct">
            {{ $t('product.complete') }}
        </el-button>
      </div>
    </el-form>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { ProductModule } from '@/store/modules/product'
import '@/assets/custom-theme/index.css'
import productName  from '@/views/components/product-name.vue'
import taxTypePulldown from '../components/tax-type-pulldown.vue'
import money from '@/views/components/money.vue'

import { Form as ElForm } from 'element-ui'

@Component({
  name: 'Product-save',
  components :{
    productName,
    taxTypePulldown,
    money
  }
})
export default class extends Vue {

  product = {
    id: ProductModule.id,
    productName: ProductModule.productName,
    taxType: ProductModule.taxType,
    purchaseUnitPrice: ProductModule.purchaseUnitPrice,
    unitPrice: ProductModule.unitPrice
  }

  private updateProduct(){
    
    (this.$refs.product as ElForm).validate(async(valid: boolean) => {
      if(valid){
        await ProductModule.UpdateProduct(this.product)
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

.complete-btn {
  float: right;
}

</style>