<template>
  <el-form-item prop="name">
    <el-input
      ref="client"
      v-model="client.name"
      :placeholder="$t('client.name')"
      name="name"
      type="text"
      tabindex="1"
      autocomplete="on"
      max="50"
      style="width:100%; margin-bottom:30px;"
    />
  </el-form-item>
</template>

<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'
import { Form as ElForm, Input } from 'element-ui'
import { Dictionary } from 'vue-router/types/router'
import { ClientModule } from '@/store/modules/client'
// import { CientSave } from '@/view/compornents/company'
import '@/assets/custom-theme/index.css'


@Component({
  name: 'CompanySave'
})
export default class extends Vue {
  private validateClientName = (rule: any, value: string, callback: Function) => {
    if (value.length < 1) {
      callback(new Error('取引先会社名を入力してください。'))
    } else if(value.length > 50){
      callback(new Error('文字数は50文字以内で入力してください。'))
    }else {
      callback()
    }
  }

  private client = {
    name: ''
  }

  private clientRules = {
    name: [{validator: this.validateClientName, trigger: 'blur' }]
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