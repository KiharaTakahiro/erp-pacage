<template>
  <div class="app-container">
    <div>{{ $t("route.supplierProduct") }}</div>
    <br />
    <br />
    <el-form
      ref="client"
      :model="client"
      autocomplete="on"
      label-position="left"
    >
      <div class="complete-btn">
        <el-button
          type="primary"
          style="width:100%;"
          @click.native.prevent="createSupplierProduct"
        >
          {{ $t("client.complete") }}
        </el-button>
      </div>
    </el-form>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { Form as ElForm } from 'element-ui'
import '@/assets/custom-theme/index.css'

@Component({
  name: "SaveSupplierProduct",
  components: {}
})
export default class extends Vue {
  supplierProduct = {
    name: ""
  };
  createSupplierProduct() {
    // TODO: 処理を記載する
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
