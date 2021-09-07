<template>
  <el-form-item
    :rules="[
      {
        required: true,
        message: '倉庫名は必ず入力してください',
        trigger: 'blur',
      },
      { max: 50, message: '文字数は50文字までにしてください' },
    ]"
    prop="name"
  >
    <el-input
      v-model="name"
      :placeholder="$t('warehouse.name')"
      type="text"
      maxlength="50"
      tabindex="1"
      autocomplete="on"
    />
  </el-form-item>
</template>

<script lang="ts">
import { Component, Vue, PropSync } from 'vue-property-decorator'
import '@/assets/custom-theme/index.css'

@Component({
  name: 'WarehouseName',
})
export default class extends Vue {
  @PropSync('WarehouseName', { type: String }) name!: string
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
