

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

<script>
import { Component, Vue, Watch } from 'vue-property-decorator'
import { Form as ElForm, Input } from 'element-ui'
import { Dictionary } from 'vue-router/types/router'
import { ClientModule } from '@/store/modules/client'
import '@/assets/custom-theme/index.css'

@Component({
  name: 'ClientSave'
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