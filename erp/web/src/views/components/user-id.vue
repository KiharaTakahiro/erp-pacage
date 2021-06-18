<template>
<div>
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
      v-on:blur="submit"
    />
</el-form-item>
</div>
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
