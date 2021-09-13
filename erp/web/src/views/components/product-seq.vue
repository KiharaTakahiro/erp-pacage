<template>
  <div>
    <el-form-item
      label="商品ID"
      prop="productSeq"
    >
      <el-input
        v-model="id"
        :placeholder="$t('product.id')"
        type="text"
        maxlength="50"
        tabindex="1"
        autocomplete="on"
        clearable
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
  @PropSync('productSeq', { type: Number }) id!: Number
}
</script>
