<template>
  <div>
    <el-form-item
      label="商品名"
      :rules="[
          {required: true, message:'商品名は必ず入力して下さい。', trigger: 'blur'},
          {max: 50, message:'50文字以内で入力してください。', trigger: 'blur'}
        ]"
      prop="productName"
    >
    <el-input
      v-model="productName"
      :placeholder="$t('product.name')"
      name="productName"
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
  name: 'productName'
})
export default class extends Vue{
  @Prop({ default: '' })
  productName!: string;

  @Emit('productNameSubmit')
  submit() {
    return this.productName
    }
}


</script>