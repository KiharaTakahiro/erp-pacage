<template>
  <div>
    <el-form-item
      label="税区分"
      prop="taxTypePulldown"
    >
    <el-select
      v-model="value"
      :placeholder="$t('product.taxType')"
      v-on:change="submit"
      >
      <el-option
        v-for="item in options"
        :key="item.key"
        :label="item.value"
        :value="item.key"
      >
      </el-option>
    </el-select>
    </el-form-item>
  </div>
</template>

<script lang='ts'>
import { Component, Vue, Prop, Emit } from 'vue-property-decorator'
import '@/assets/custom-theme/index.css'
import { getCode } from '@/api/system'

@Component({
  name: 'taxTypePulldown'
})

export default class extends Vue{

  options = []

  @Prop({ default: null })
  value!: number;

  created() {
    this.getCode()
  }

  private async getCode(){
    const { data } = await getCode({codeType: "TaxType"})
    this.options = data.codes

  }

  @Emit('taxTypePulldownSubmit')
  submit() {
    return this.value
  }
}

</script>