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
        :key="item.value"
        :label="item.label"
        :value="item.value"
        :disabled="item.disabled"
      >
      </el-option>
    </el-select>
    </el-form-item>
  </div>
</template>

<script lang='ts'>
import { Component, Vue, Prop, Emit } from 'vue-property-decorator'
import '@/assets/custom-theme/index.css'

@Component({
  name: 'taxTypePulldown'
})

export default class extends Vue{

  options= [{
    value: '1',
    label: '税金なし',
    disabled: true
  }, {
    value: '2',
    label: '軽減税率'
  }, {
    value: '3',
    label: '通常'
  }, ]

  @Prop({ default: '' })
  value!: string;

  @Emit('taxTypePulldownSubmit')
  submit() {
    return this.value
    }
}

</script>