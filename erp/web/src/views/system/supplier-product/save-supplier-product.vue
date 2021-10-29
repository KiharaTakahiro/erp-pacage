<template>
  <div class="app-container">
    <div>{{ $t('route.newSupplierProduct') }}</div>
    <br />
    <br />
    <el-form
      :model="supplierProduct"
      ref="supplierProduct"
      autocomplete="on"
      label-position="left"
    >
      <id-search
        style="width:50%;"
        label="仕入先Seq"
        :targetId.sync="targetSupplierSeq" />
      <product-name 
      :required="true"
      :supplierProductName.sync="supplierProduct.supplierProductName" />
      <tax-type-pulldown :taxTypeValue.sync="supplierProduct.taxType" />
      <money
        label="仕入料金"
        prop="purchaseUnitPrice"
        :required="true"
        :placeholder="$t('supplierProduct.purchaseUnitPrice')"
        :priceValue.sync="supplierProduct.purchaseUnitPrice"
      />
      <div class="complete-btn">
        <el-button
          type="primary"
          style="width:100%;"
          @click.native.prevent="createSupplierProduct"
        >
          {{ $t('supplierProduct.complete') }}
        </el-button>
      </div>
    </el-form>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { Form as ElForm } from 'element-ui'
import '@/assets/custom-theme/index.css'
import ProductName from '@/views/components/product-name.vue'
import taxTypePulldown from '@/views/components/tax-type-pulldown.vue'
import money from '@/views/components/money.vue'
import { SupplierProductModule } from '@/store/modules/supplier-product'
import IdSearch from '@/views/components/id-search.vue'

@Component({
  name: 'supplier-productsave',
  components: {
    IdSearch,
    ProductName,
    taxTypePulldown,
    money
  }
})
export default class extends Vue {
  supplierProduct = {
    supplierSeq: '',
    supplierProductName: '',
    taxType: 1,
    purchaseUnitPrice: 0
  }

  private createSupplierProduct() {
    (this.$refs.supplierProduct as ElForm).validate(async (valid: boolean) => {
      if (valid) {
        await SupplierProductModule.CreateSupplierProduct(this.supplierProduct)
        this.$router
          .push({
            path: 'supplier-productlist'
          })
          .catch(err => {
            console.warn(err)
          })
        this.$message({
          message: this.$t('components.createSupplierProduct').toString(),
          type: 'success'
        })
        console.log(this.supplierProduct)
      } else {
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

.app-container {
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
