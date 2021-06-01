<template>
  <el-form-item 
      prop="name"
      @submit="submit"
    >
    <el-input
      :model="company"
      v-model='companyName'
      :placeholder="$t('client.name')"
      type="text"
      maxlength="50"
      tabindex="1"
      autocomplete="on"
      style="width:100%; margin-bottom:30px;"


    />
  </el-form-item>

</template>

<script lang="ts">
import { Component, Vue, Watch, Prop, Emit } from 'vue-property-decorator'
import { Form as ElForm, Input } from 'element-ui'
import { ClientModule } from '@/store/modules/client'
// import { CientSave } from '@/view/compornents/company'
import '@/assets/custom-theme/index.css'


@Component({
  name: 'CompanyName'
})
export default class extends Vue {

  @Prop({ default: '' })
  companyName!: string;

  @Emit()
  submit() {
    return this.companyName;
  }




}



</script>

<style lang="scss" scoped>
.field-label {
  vertical-align: middle;
}

.app-container{
  width: 50%;
}

.box-card {
  width: 400px;
  max-width: 100%;
  margin: 20px auto;
}

.block {
  padding: 30px 24px;
}

.tag-item {
  margin-right: 15px;
}

.complete-btn{
  float: right;
}

</style>