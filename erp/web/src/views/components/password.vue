<template>
  <div>
    <el-form-item
      label="Password"
      prop="pass"
    >
      <el-input
        type="password"
        v-model="pass"
        autocomplete="off"
        />
    </el-form-item>
    <el-form-item
      label="Confirm"
      prop="checkPass">
      <el-input
        type="password"
        v-model="checkPass"
        autocomplete="off"
      />
    </el-form-item>
  </div>

</template>

<script lang='ts'>
import { Component, Vue, Watch, Prop, Emit } from 'vue-property-decorator'
import '@/assets/custom-theme/index.css'


@Component({
  name: 'Password'
})

export default class extends Vue{

  @Prop({ default: '' })
  pass!: string;

  @Prop({ default: '' })
  checkPass!: string;


}



</script>
