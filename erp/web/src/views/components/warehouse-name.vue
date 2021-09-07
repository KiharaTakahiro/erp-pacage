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
  name: 'warehouseName',
})
export default class extends Vue {
  @PropSync('warehouseName', { type: String }) name!: string
}
</script>