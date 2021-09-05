<template>
  <el-form-item v-bind:label="label">
    <el-date-picker
      :value="dateVal"
      @input="changeDateValue"
      type="date"
      :placeholder="label"
      :clearable="false"
    >
    </el-date-picker>
  </el-form-item>
</template>

<script lang="ts">
import { Component, Vue, Prop, PropSync } from 'vue-property-decorator'
import '@/assets/custom-theme/index.css'

@Component({
  name: 'dateForm'
})
export default class extends Vue {
  @PropSync('date', { type: String }) dateVal!: string

  @Prop({ default: '日付' })
  label!: string

  // 配送日変更時の処理
  private changeDateValue(date: any) {
    this.dateVal = `${date.getFullYear()}-${date.getMonth()+1}-${date.getDate()}`
  }
}
</script>
