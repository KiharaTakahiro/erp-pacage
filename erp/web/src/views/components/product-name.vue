<template>
  <div>
    <el-form-item
      label="商品名"
      :rules="[
        {
          required: true,
          message: '商品名は必ず入力して下さい。',
          trigger: 'blur'
        },
        { max: 50, message: '50文字以内で入力してください。', trigger: 'blur' }
      ]"
      prop="productName"
    >
      <el-input
        v-model="name"
        :placeholder="$t('product.name')"
        type="text"
        maxlength="50"
        tabindex="1"
        autocomplete="on"
      />
    </el-form-item>
  </div>
</template>

<script lang="ts">
import { Component, Vue, PropSync } from 'vue-property-decorator'
import '@/assets/custom-theme/index.css'

@Component({
  name: 'productName'
})
export default class extends Vue {
  @PropSync('productName', { type: String }) name!: string
}
</script>
