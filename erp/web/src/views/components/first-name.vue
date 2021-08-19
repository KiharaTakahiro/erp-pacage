<template>
<el-form-item 
  label="名前"
  :rules="[
    { required: true, message: '名前は必ず入力してください', trigger: 'blur'}
  ]"
  prop="firstName"
  >
  <el-input
    v-model="firstName"
    :placeholder="$t('user.firstName')"
    name="firstName"
    type="text"
    tabindex="1"
    autocomplete="on"
    max="50"
    v-on:blur="submit"
    />
</el-form-item>

</template>

<script lang='ts'>
import { Component, Vue, Prop, Emit } from 'vue-property-decorator'
import '@/assets/custom-theme/index.css'

@Component({
  name: 'FirstName'
})

export default class extends Vue{
  @Prop({ default: '' })
  firstName!: string;

  @Emit('firstNameSubmit')
  submit() {
    return this.firstName
  }

}


</script>
