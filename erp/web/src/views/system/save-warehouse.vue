<template>
  <div class="app-container">
    <div>{{ $t('route.newWarehouse') }}</div>
    <br />
    <br />
    <el-form
      ref="warehouse"
      :model="warehouse"
      autocomplete="on"
      label-position="left"
    >
      <warehouse-name :warehouseName.sync="warehouse.warehouseName" />
      <div class="complete-btn">
        <el-button
          type="primary"
          style="width:100%;"
          @click.native.prevent="createWarehouse"
        >
          {{ $t('client.complete') }}
        </el-button>
      </div>
    </el-form>
  </div>
</template>


<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { Form as ElForm } from 'element-ui'
import '@/assets/custom-theme/index.css'
import warehouseName from '@/views/components/warehouse-name.vue'
import { WarehouseModule } from '@/store/modules/warehouse'


@Component({
  name: 'Warehouse-save',
  components: {
    warehouseName
  }
})
export default class extends Vue {
  warehouse = {
    warehouseName: '',
  }
  private createWarehouse() {
    (this.$refs.warehouse as ElForm).validate(async (valid: boolean) => {
      if (valid) {
        await WarehouseModule.CreateWarehouse(this.warehouse)
        this.$router
          .push({
            path: 'warehouse'
          })
          .catch(err => {
            console.warn(err)
          })
        this.$message({
          message: this.$t('components.createWarehouse').toString(),
          type: 'success'
        })
        console.log(this.warehouse)
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

