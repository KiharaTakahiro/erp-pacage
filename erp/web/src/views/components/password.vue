<template>
  <div>
    <el-form-item
      label="パスワード"
      prop="password"
      :rules="[
        {
          required: true,
          message: 'パスワードは必ず入力してください',
          trigger: 'blur'
        }
      ]"
    >
      <el-input type="password" autocomplete="off" v-model="pass1" max="50" />
    </el-form-item>
    <el-form-item
      label="確認"
      prop="password2"
      :rules="[
        { validator: validatePass, trigger: 'blur' },
        {
          required: true,
          message: '確認用パスワードは必ず入力してください',
          trigger: 'blur'
        }
      ]"
    >
      <el-input type="password" v-model="pass2" autocomplete="off" />
    </el-form-item>
  </div>
</template>

<script lang="ts">
import { Component, Vue, PropSync } from 'vue-property-decorator'

import '@/assets/custom-theme/index.css'

@Component({
  name: 'Password'
})
export default class extends Vue {
  @PropSync('password', { type: String }) pass1!: string
  @PropSync('password2', { type: String }) pass2!: string

  private validatePass = (rule: any, value: string, callback: Function) => {
    // NOTE: Property 'XXX' does not exist on type 'CombinedVueInstanceが発生するのを防ぐ
    // @ts-ignore
    let pass1_input = this.password

    // 必須チェックはそれぞれ別にするためこの処理では空文字があれば除外
    if (value === '') {
      callback(new Error('確認用パスワードは必ず入力してください'))
      return
    }

    // 確認用のパスワードと入力したパスワードが異なる場合はエラー
    if (pass1_input !== value) {
      callback(new Error('パスワードが一致しません'))
      return
    }
    callback()
  }
}
</script>
