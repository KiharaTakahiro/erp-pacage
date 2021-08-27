<template>
  <div>
    <el-form-item
      label="パスワード"
      prop="password"
      :rules="[ 
      { required: true, message: 'パスワードは必ず入力してください', trigger: 'blur'}
      ]"
    >
      <el-input
        type="password"
        autocomplete="off"
        v-model="pass1"
        max="50"
        />
    </el-form-item>
    <el-form-item
      label="確認"
      prop="password2"
      :rules="[ 
          { validator: validatePass2, trigger: 'blur' },
          { required: true, message: '確認用パスワードは必ず入力してください', trigger: 'blur'}
          ]"
      >
      <el-input
        type="password"
        v-model="pass2"
        autocomplete="off"
        
      />
    </el-form-item>
  </div>

</template>

<script lang='ts'>
import { Component, Vue, Prop, Emit } from 'vue-property-decorator'

import '@/assets/custom-theme/index.css'


@Component({
  name: 'Password'
})

export default class extends Vue{

  @Prop({ default: '' })
  password!: string;

  @Prop({ default: '' })
  password2!: string;
  
  
  get pass1() {
    return this.password
  }

  set pass1(value) {
    this.$emit('passSubmit', value)
  }

  
  get pass2() {
    return this.password2
  }

  set pass2(value) {
    this.$emit('checkPassSubmit', value)
  }

  private validatePass2 = (rule: any, value: string, callback: Function) => {
    if (value === '') {
      callback(new Error('確認用パスワードを入力してください'));
    } else if (value !== this.password) {
      callback(new Error('パスワードが一致しません'));
    } else {
      callback();
    }
  }
}



</script>
