<template>
  <div>
    <el-form-item
      label="税区分"
      prop="taxTypePulldown"
    >
    <el-select
      v-model="taxTypePulldown"
      :placeholder="$t('route.taxType')"
      >
      <el-option
        v-for="item in taxTypeList"
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

  taxTypeList= [{
    value: 'Option1',
    label: 'Option1'
  }, {
    value: 'Option2',
    label: 'Option2'
  }, {
    value: 'Option3',
    label: 'Option3'
  }, ]

  @Prop({ default: '' })
  TaxTypePulldown!: string;
}