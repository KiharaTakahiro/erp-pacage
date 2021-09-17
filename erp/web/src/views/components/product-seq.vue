<template>
  <div>
    <el-form-item
      label="商品ID"
      :rules="[
        {
          pattern: /^(0|[1-9]|[1-9][0-9]+)$/,
          message: '半角数字で入力してください。',
          trigger: 'blur',
        }
      ]"
      prop="productSeq"
    >
      <el-input
        v-model="id"
        :placeholder="$t('product.id')"
        type="text"
      />
    </el-form-item>
  </div>
</template>

<script lang="ts">
import { Component, Vue, PropSync } from 'vue-property-decorator'
import '@/assets/custom-theme/index.css'

@Component({
  name: 'productSeq'
})
export default class extends Vue {
  @PropSync('productSeq', { type: String }) id!: String
}
</script>
