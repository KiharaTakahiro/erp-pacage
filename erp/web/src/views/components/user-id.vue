<template>
<el-form-item 
  label="ユーザーID"
  :rules="[
      { required: true, message: 'ユーザーIDは必ず入力してください', trigger: 'blur'}
    ]"
  prop="userId"
>
  <el-input
    v-model="userId"
    :placeholder="$t('user.id')"
    name="userId"
    type="text"
    tabindex="1"
    autocomplete="on"
    maxlength="50"
    v-on:blur="submit"
    style="width:100%; margin-bottom:30px;"
  />
</el-form-item>

</template>

<script lang='ts'>
import { Component, Vue, Watch, Prop, Emit } from 'vue-property-decorator'
import '@/assets/custom-theme/index.css'
@Component({
  name: 'UserId'
})

export default class extends Vue{
  @Prop({ default: '' })
  userId!: string;

  private submit(): void {
    this.$emit('userIdSubmit', this.userId)
  }

}


</script>
