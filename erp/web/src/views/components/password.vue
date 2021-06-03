<template>
  <div>
    <el-form-item
      label="Password"
      prop="pass"
      :rules="[
        { validator: validatePass, trigger: 'blur'}
      ]"
    >
      <el-input
        type="password"
        v-model="pass"
        autocomplete="off"
        v-on:blur="submitPass"
        />
    </el-form-item>
    <el-form-item
      label="Confirm"
      prop="checkPass"
      :rules="[
        { validator: validatePass2, trigger: 'blur' }
        ]"
      >
      <el-input
        type="password"
        v-model="checkPass"
        autocomplete="off"
        v-on:blur="submitCheckPass"
      />
    </el-form-item>
  </div>

</template>

<script lang='ts'>
import { Component, Vue, Watch, Prop, Emit } from 'vue-property-decorator'
import '@/assets/custom-theme/index.css'
import { Function } from 'node_modules/@types/lodash';


@Component({
  name: 'Password'
})

export default class extends Vue{

  @Prop({ default: '' })
  pass!: string;

  @Prop({ default: '' })
  checkPass!: string;

  private validatePass = (rule: any, value: string, callback: Function) => {
    if (value === '') {
      callback(new Error('Please input the password'));
    } else {
      callback();
    }
  }
  private validatePass2 = (rule: any, value: string, callback: Function) => {
    if (value === '') {
      callback(new Error('Please input the password again'));
    } else if (value !== this.pass) {
      callback(new Error('Two inputs don\'t match!'));
    } else {
      callback();
    }
  }

  private submitPass(): void {
    this.$emit('passSubmit', this.pass)
  }

  private submitCheckPass(): void {
    this.$emit('checkPassSubmit', this.checkPass)
  }


}



</script>
