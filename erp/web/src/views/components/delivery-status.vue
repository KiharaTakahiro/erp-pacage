<template>
  <el-form-item label="配送状況">
    <el-select
      v-model="statusVal"
      filterable
      clearable
      placeholder="配送状況"
    >
      <el-option
        v-for="status in RecivedOrderSatsus"
        :key="status.key"
        :label="status.value"
        :value="status.key"
      >
      </el-option>
    </el-select>
  </el-form-item>
</template>
<script lang='ts'>
import { Component, Vue, PropSync, Prop, Emit } from 'vue-property-decorator'
import { getCode } from '@/api/system'

@Component({
  name: 'DelivaryStatus',
  components: {
  }
})
export default class extends Vue {

  // 配送状況
  @PropSync('status', { type: String }) statusVal!: string
  
  // 作成時
  created() {
    this.getCode()
  }

  private RecivedOrderSatsus = []

  private async getCode() {
    const { data } = await getCode({ codeType: 'RacivedOrderStatus' })
    this.RecivedOrderSatsus = data.codes
  }
}
</script>
<style lang="scss" scoped>

</style>