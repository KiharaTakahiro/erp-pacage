<template>
<el-form-item 
  :rules="[
      { required: true, message: '会社名は必ず入力してください', trigger: 'blur'}{ max: 50, message: '文字数は50文字までにしてください'}
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
    v-on:blur=""
    style="width:100%; margin-bottom:30px;"
  />
</el-form-item>

</template>

<script lang='ts'>
import { Component, Vue, Watch, Prop, Emit } from 'vue-property-decorator'
import '@/assets/custom-theme/index.css'

@Comment({
  name: UserId
})

export default class extends Vue{
  @Prop({ default: '' })
  userId!: string;

  private UserId(): void {
    this.$emit('company-name', this.userId)
  }

}


</script>
