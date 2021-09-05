<template>
<el-form-item 
  label="名前"
  :rules="[
    { required: true, message: '名前は必ず入力してください', trigger: 'blur'}
  ]"
  prop="firstName"
  >
  <el-input
    v-model="name"
    :placeholder="$t('user.firstName')"
    name="firstName"
    type="text"
    tabindex="1"
    autocomplete="on"
    max="50"
    />
</el-form-item>

</template>

<script lang='ts'>
import { Component, Vue, PropSync } from 'vue-property-decorator'
import '@/assets/custom-theme/index.css'

@Component({
  name: 'FirstName'
})

export default class extends Vue {
  @PropSync('firstName', { type: String }) name!: string
}
</script>
