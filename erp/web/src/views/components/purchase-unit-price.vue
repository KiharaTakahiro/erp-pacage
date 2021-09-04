<template>
  <div>
    <el-form-item
      label="仕入料金"
      :rules="[
          { required: true, message:'仕入料金は必ず入力して下さい。', trigger: 'blur'},
          { pattern: /^(0|[1-9]|[1-9][0-9]+)$/, message:'半角数字で入力してください。', trigger: 'blur'}
        ]"
      prop="purchaseUnitPrice"
    >
    <el-input
      v-model="price"
      :placeholder="$t('product.purchaseUnitPrice')"
      type="text"
    />
    </el-form-item>
  </div>  
</template>

<script lang='ts'>
import { Component, Vue, PropSync } from 'vue-property-decorator'
import '@/assets/custom-theme/index.css'

@Component({
  name: 'purchaseUnitPrice'
})
export default class extends Vue{
  // NOTE: テキストボックスであるためstringで入ってしまうのでエラーとなるのを
  // 防止するべくStringを許容しているより良い方法があればそちらに移行したい
  @PropSync('purchaseUnitPrice', {type: [Number, String]}) price!: number

}
</script>