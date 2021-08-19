<template>
<el-form-item
  label="名字"
  :rules="[
    { required: true, message: '名字は必ず入力してください', trigger: 'blur'}
  ]"
  prop="lastName"
  >
  <el-input
    v-model="lastName"
    :placeholder="$t('user.lastName')"
    name="lastName"
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
  name: 'LastName'
})

export default class extends Vue{
  @Prop({ default: '' })
  lastName!: string;

  @Emit('lastNameSubmit')
  submit(){
    return this.lastName
  }
}


</script>
