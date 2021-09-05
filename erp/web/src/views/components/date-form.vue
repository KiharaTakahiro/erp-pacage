<template>
  <el-form-item v-bind:label="label">
    <el-date-picker
      v-model="dateVal"
      type="date"
      v-bind:placeholder="label"
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
  @PropSync('date', { type: String }) dateSubmit!: string

  @Prop({ default: '日付' })
  label!: string
}
</script>
