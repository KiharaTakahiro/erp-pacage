<template>
  <div>
    <el-form-item
      label="定価"
      :rules="[
          { required: true, message:'定価は必ず入力して下さい。', trigger: 'blur'},
          { pattern: /^(0|[1-9]|[1-9][0-9]+)$/, message:'半角数字で入力してください。', trigger: 'blur'}
        ]"
       prop="unitPrice">
      <el-input
        v-model="price"
        :placeholder="$t('product.unitPrice')"
        name="unitPrice"
        type="text"/>
    </el-form-item>
  </div>  
</template>

<script lang='ts'>
import { Component, Vue, PropSync } from 'vue-property-decorator'
import '@/assets/custom-theme/index.css'

@Component({
  name: 'unitPrice'
})
export default class extends Vue{
  // NOTE: テキストボックスであるためstringで入ってしまうのでエラーとなるのを
  // 防止するべくStringを許容しているより良い方法があればそちらに移行したい
  @PropSync('unitPrice', {type: [Number, String]}) price!: number
}
</script>