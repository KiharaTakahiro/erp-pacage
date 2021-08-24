<template>
  <div>
    <el-form-item
      label="仕入れ料金"
      prop="purchaceUnitPrice"
    >
    <el-input
      v-model="purchaceUnitPrice"
      :placeholder="$t('product.purchaceUnitPrice')"
      name="purchaceUnitPrice"
      type="text"
      v-on:blur="submit"
    />
    </el-form-item>
  </div>  
</template>

<script lang='ts'>
import { Component, Vue, Prop, Emit } from 'vue-property-decorator'
import '@/assets/custom-theme/index.css'

@Component({
  name: 'purchaceUnitPrice'
})
export default class extends Vue{
  @Prop({ default: '' })
  purchaceUnitPrice!: bigint;

  @Emit('purchaceUnitPriceSubmit')
  submit() {
    return this.purchaceUnitPrice
    }
}