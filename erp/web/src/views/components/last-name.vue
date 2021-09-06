<template>
  <el-form-item
    label="名字"
    :rules="[
      { required: true, message: '名字は必ず入力してください', trigger: 'blur' }
    ]"
    prop="lastName"
  >
    <el-input
      v-model="name"
      :placeholder="$t('user.lastName')"
      name="lastName"
      type="text"
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
  name: 'LastName'
})
export default class extends Vue {
  @PropSync('lastName', { type: String }) name!: string
}
</script>
