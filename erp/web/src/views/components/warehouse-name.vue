<template>
  <div>
    <el-form-item
      label="倉庫名"
      :rules="[
        {
          required: required,
          message: '倉庫名は必ず入力して下さい。',
          trigger: 'blur'
        },
        {max: 50, message: '50文字以内で入力してください。', trigger: 'blur'}
      ]"
      prop="warehouseName"
    >
      <el-input
        v-model="name"
        :placeholder="$t('warehouse.name')"
        type="text"
        maxlength="50"
        tabindex="1"
        autocomplete="on"
      />
    </el-form-item>
  </div>
</template>

<script lang="ts">
import { Component, Vue, PropSync, Prop } from 'vue-property-decorator'
import '@/assets/custom-theme/index.css'

@Component({
  name: 'warehouseName'
})
export default class extends Vue {
  @Prop({ default: false })
  required!: boolean

  @PropSync('warehouseName', { type: String }) name!: string
}
</script>
