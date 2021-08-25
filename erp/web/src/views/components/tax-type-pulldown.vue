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
    value: '税金なし',
    label: '税金なし'
  }, {
    value: '軽減税率',
    label: '軽減税率'
  }, {
    value: '通常',
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