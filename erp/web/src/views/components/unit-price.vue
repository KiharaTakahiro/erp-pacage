<template>
  <div>
    <el-form-item
      label="定価"
      :rules="[
          {required: true, message:'定価は必ず入力して下さい。', trigger: 'blur'},
          {pattern: /^(0|[1-9]|[1-9][0-9]+)$/, message:'半角数字で入力してください。', trigger: 'blur'}
        ]"
      prop="unitPrice"
    >
    <el-input
      v-model="unitPrice"
      :placeholder="$t('product.unitPrice')"
      name="unitPrice"
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
  name: 'unitPrice'
})
export default class extends Vue{
  @Prop({ default: 0n })
  unitPrice!: bigint;

  @Emit('unitPriceSubmit')
  submit() {
    return this.unitPrice
    }
}


</script>