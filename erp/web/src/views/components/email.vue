<template>
<el-form-item 
  label="メールアドレス"
  :rules="[
    { required: true, message: 'メールアドレスは必ず入力してください', trigger: 'blur'}
  ]"
  prop="email"
  >
  <el-input
    v-model="email"
    :placeholder="$t('user.email')"
    name="email"
    type="text"
    tabindex="1"
    autocomplete="on"
    max="50"
    v-on:blur="submit"
    />
</el-form-item>

</template>

<script lang='ts'>
import { Component, Vue, Watch, Prop, Emit } from 'vue-property-decorator'
import '@/assets/custom-theme/index.css'

@Component({
  name: 'Email'
})

export default class extends Vue{
  @Prop({ default: '' })
  email!: string;

  private submit(): void {
    this.$emit('emailSubmit', this.email)
  }

}


</script>
