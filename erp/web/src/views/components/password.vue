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
        v-model="password"
        autocomplete="off"
        v-on:blur="submitPass"
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
        v-model="password2"
        autocomplete="off"
        v-on:blur="submitCheckPass"
        
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

  @Emit('passSubmit')
    submitPass(){
    return this.password
  }

  @Emit('checkPassSubmit')
    submitCheckPass(){
    return this.password2
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
