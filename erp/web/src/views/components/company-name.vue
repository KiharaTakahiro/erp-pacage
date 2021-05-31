<template>
  <el-form-item 
    :rules="[
      { required: true, message: '会社名を入力してください'},
      ]"
      prop="name"
    >
    <el-input
      ref="client"
      :rules="companyRules"
      :model="company"
      v-model="company.name"
      :placeholder="$t('client.name')"
      name="name"
      type="text"
      tabindex="1"
      autocomplete="on"
      max="50"
      style="width:100%; margin-bottom:30px;"
      v-on:blur='companyRules'
    />
  </el-form-item>

</template>

<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'
import { Form as ElForm, Input } from 'element-ui'
import { ClientModule } from '@/store/modules/client'
// import { CientSave } from '@/view/compornents/company'
import '@/assets/custom-theme/index.css'


@Component({
  name: 'CompanyName'
})
export default class extends Vue {
  
  private company = {
    name: ''
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