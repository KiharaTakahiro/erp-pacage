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
      v-model="name"
      :placeholder="$t('product.name')"
      name="productName"
      type="text"
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

  
  get name() {
    return this.productName
  }

  set name(value) {
    this.$emit('productNameSubmit', value)
  }

}


</script>