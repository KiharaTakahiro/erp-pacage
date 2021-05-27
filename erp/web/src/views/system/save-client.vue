<template>
  <div class="app-container">
    <div>{{ $t("route.newClient") }}</div>
    <br>
    <br>
    <el-form
      ref="client"
      :model="client"
      :rules="clientRules"
      autocomplete="on"
      label-position="left"
    >
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
      <div class="complete-btn">
        <el-button
            :loading="loading"
            type="primary"
            style="width:100%;"
            @click.native.prevent="createClient"
          >
            {{ $t('client.complete') }}
        </el-button>
      </div>


    </el-form>

    
    
    
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'
import { Form as ElForm, Input } from 'element-ui'
import { Dictionary } from 'vue-router/types/router'
import { ClientModule } from '@/store/modules/client'
import '@/assets/custom-theme/index.css'


@Component({
  name: 'Client-save'
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

  private otherQuery: Dictionary<string> = {}

  private createClient(){
    (this.$refs.client as ElForm).validate(async(valid: boolean) => {
      if(valid){
        await ClientModule.Create(this.client)
        this.$router.push({
          path: 'clinet'
        }).catch(err => {
          console.warn(err)
        })
      }else {
        return false
      }
    })
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