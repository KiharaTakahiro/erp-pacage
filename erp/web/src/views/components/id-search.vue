<template>
  <el-form-item
    v-bind:label="label"
  >
    <el-input
      v-model="id"
      name="recivedOrderId"
      type="text"
      :placeholder="label"
      style="width:15%;"
    />
  </el-form-item>
</template>

<script lang="ts">
import { Component, Vue, PropSync, Prop } from 'vue-property-decorator'
import '@/assets/custom-theme/index.css'

@Component({
  name: 'idSearch'
})
export default class extends Vue {
  @PropSync('targetId', { type: String }) id!: string
  @Prop({default: '' }) label!: string
}
</script>
