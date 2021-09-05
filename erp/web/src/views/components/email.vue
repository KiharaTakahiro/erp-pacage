<template>
  <el-form-item
    label="メールアドレス"
    :rules="[
      {
        required: true,
        message: 'メールアドレスは必ず入力してください',
        trigger: 'blur'
      },
      {
        type: 'email',
        message: 'メールアドレスが不正です',
        trigger: ['blur', 'change']
      }
    ]"
    prop="email"
  >
    <el-input
      v-model="mail"
      :placeholder="$t('user.email')"
      name="email"
      type="email"
      tabindex="1"
      autocomplete="on"
      max="50"
    />
  </el-form-item>
</template>

<script lang="ts">
import { Component, Vue, PropSync } from 'vue-property-decorator'
import '@/assets/custom-theme/index.css'

@Component({
  name: 'Email'
})
export default class extends Vue {
  @PropSync('email', { type: String }) mail!: string
}
</script>
