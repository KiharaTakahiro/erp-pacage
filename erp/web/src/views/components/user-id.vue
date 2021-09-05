<template>
<div>
  <el-form-item 
    label="ユーザーID"
    :rules="[
        { required: true, message: 'ユーザーIDは必ず入力してください', trigger: 'blur'}
      ]"
    prop="userId"
  >
    <el-input
      v-model="id"
      :placeholder="$t('user.id')"
      name="userId"
      type="text"
    />
</el-form-item>
</div>
</template>

<script lang='ts'>
import { Component, Vue, PropSync } from 'vue-property-decorator'
import '@/assets/custom-theme/index.css'

@Component({
  name: 'UserId'
})
export default class extends Vue {
  @PropSync('userId', { type: String }) id!: string
}
</script>
