<template>
  <div>
    <el-form-item
      :label="label"
      :rules="[
        {
          required: required,
          message: `${label}は必ず入力して下さい。`,
          trigger: 'blur'
        },
        {
          pattern: /^(0|[1-9]|[1-9][0-9]+)$/,
          message: '半角数字で入力してください。',
          trigger: 'blur'
        }
      ]"
      :prop="prop"
    >
      <el-input :placeholder="placeholder" v-model="price" type="text" />
    </el-form-item>
  </div>
</template>

<script lang="ts">
import { Component, Vue, PropSync, Prop } from 'vue-property-decorator'
import '@/assets/custom-theme/index.css'

@Component({
  name: 'money'
})
export default class extends Vue {
  @Prop({ default: '' })
  label!: string

  @Prop({ default: '' })
  prop!: string

  @Prop({ default: '' })
  placeholder!: string

  @Prop({ default: false })
  required!: boolean

  // NOTE: テキストボックスであるためstringで入ってしまうのでエラーとなるのを
  // 防止するべくStringを許容しているより良い方法があればそちらに移行したい
  @PropSync('priceValue', { type: [Number, String] }) price!: number
}
</script>
