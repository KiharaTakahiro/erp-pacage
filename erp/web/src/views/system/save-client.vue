<template>
  <div class="app-container">
    <div>{{ $t("route.newClient") }}</div>
    <br>
    <br>
    <el-form
      ref="client"
      :model="client"
      autocomplete="on"
      label-position="left"
    >
      <company-name
        :companyName="client.name"
        @value='value'
        />
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
import CompanyName from '@/views/components/company-name.vue'
import '@/assets/custom-theme/index.css'
import { log } from 'node:console'

@Component({
  name: 'Client-save',
  components: {
    CompanyName
  }
})
export default class extends Vue {

  // name = ''

  // private value(value: any): void {
  //   this.name = value
  //   console.log(this.name)
  // }

  client = {
    name: 'this.name'
  }
  
  

  private createClient(){
    (this.$refs.client as ElForm).validate(async(valid: boolean) => {
      if(valid){
        await ClientModule.Create(client.name)
        this.$router.push({
          path: 'clinet' 
        }).catch(err => {
          console.warn(err)
        })
      this.$message({
      message: this.$t('components.createClients').toString(),
      type: 'success'
    })
      }else {
        this.$message({
        message: this.$t('components.validation').toString(),
        type: 'error'
        })
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