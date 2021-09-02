<template>
  <div class="app-container">
    <div>{{ $t("route.editProduct") }}</div>
    <br>
    <br>
    <el-form
      ref="product"
      :model="product"
      autocomplete="on"
      label-position="left"
    >
      <div class="complete-btn">
        <el-button
            type="primary"
            style="width:100%;"
            @click.native.prevent="updateProduct"
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
import { ProductModule } from '@/store/modules/product'
import '@/assets/custom-theme/index.css'
import { log } from 'node:console'
import { toNamespacedPath } from 'node:path'

@Component({
  name: 'Product-save',
  components: {
    CompanyName
  }
})
export default class extends Vue {

  product = {
    id: ProductModule.id,
    name: ProductModule.name,
    taxType: ProductModule.taxType,
    purchaseUnitPrice: ProductModule.purchaseUnitPrice,
    unitPrice: ProductModule.unitPrice
  }

  private conpanyName(name: any): void {
    this.$store.commit('SET_NAME', name)
    this.product.name = ProductModule.name
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

.complete-btn{
  float: right;
}

</style>