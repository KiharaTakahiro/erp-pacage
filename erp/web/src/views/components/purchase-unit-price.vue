<template>
  <div>
    <el-form-item
      label="仕入料金"
      :rules="[
          {required: true, message:'仕入料金は必ず入力して下さい。', trigger: 'blur'},
          {pattern: /^(0|[1-9]|[1-9][0-9]+)$/, message:'半角数字で入力してください。', trigger: 'blur'}
        ]"
      prop="purchaseUnitPrice"
    >
    <el-input
      v-model="purchaseUnitPrice"
      :placeholder="$t('product.purchaseUnitPrice')"
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
  name: 'purchaseUnitPrice'
})
export default class extends Vue{
  @Prop({ default: null })
  purchaseUnitPrice!: bigint;

  @Emit('purchaseUnitPriceSubmit')
  submit() {
    return this.purchaseUnitPrice
    }
}

</script>