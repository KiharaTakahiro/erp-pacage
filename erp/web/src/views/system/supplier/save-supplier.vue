<template>
  <div class="app-container">
    <div>{{ $t('route.newSupplier') }}</div>
    <br />
    <br />
    <el-form
      :model="supplier"
      ref="supplier"
      autocomplete="on"
      label-position="left"
    >
      <supplier-name 
      :required="true"
      :supplierName.sync="supplier.supplierName" />
      <div class="complete-btn">
        <el-button
          type="primary"
          style="width:100%;"
          @click.native.prevent="createSupplier"
        >
          {{ $t('supplier.complete') }}
        </el-button>
      </div>
    </el-form>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { Form as ElForm } from 'element-ui'
import '@/assets/custom-theme/index.css'
import supplierName from '@/views/components/supplier-name.vue'
import { SupplierModule } from '@/store/modules/supplier'

@Component({
  name: 'supplier-save',
  components: {
    supplierName
  }
})
export default class extends Vue {
  supplier = {
    supplierName: ''
  }

  private createSupplier() {
    (this.$refs.supplier as ElForm).validate(async (valid: boolean) => {
      if (valid) {
        await SupplierModule.CreateSupplier(this.supplier)
        this.$router
          .push({
            path: 'supplier-list'
          })
          .catch(err => {
            console.warn(err)
          })
        this.$message({
          message: this.$t('components.createSupplier').toString(),
          type: 'success'
        })
        console.log(this.supplier)
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
