<template>
  <div class="app-container">
    <div>{{ $t('route.editSupplierProduct') }}</div>
    <br />
    <br />
    <el-form
      ref="supplierProduct"
      :model="supplierProduct"
      autocomplete="on"
      label-position="left"
    >
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
          @click.native.prevent="updateSupplierProduct"
        >
          {{ $t('supplierProduct.complete') }}
        </el-button>
      </div>
    </el-form>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { SupplierProductModule } from '@/store/modules/supplier-product'
import '@/assets/custom-theme/index.css'
import productName from '@/views/components/product-name.vue'
import taxTypePulldown from '@/views/components/tax-type-pulldown.vue'
import money from '@/views/components/money.vue'
import { Form as ElForm } from 'element-ui'

@Component({
  name: 'SupplierProduct-save',
  components: {
    productName,
    taxTypePulldown,
    money
  }
})
export default class extends Vue {
  supplierProduct = {
    id: SupplierProductModule.id,
    supplierProductName: SupplierProductModule.supplierProductName,
    taxType: SupplierProductModule.taxType,
    purchaseUnitPrice: SupplierProductModule.purchaseUnitPrice
  }

  private updateSupplierProduct() {
    (this.$refs.supplierProduct as ElForm).validate(async (valid: boolean) => {
      if (valid) {
        await SupplierProductModule.UpdateSupplierProduct(this.supplierProduct)
        this.$router
          .push({
            path: 'supplier-product-list'
          })
          .catch(err => {
            console.warn(err)
          })
        this.$message({
          message: this.$t('components.createSupplierProduct').toString(),
          type: 'success'
        })
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
