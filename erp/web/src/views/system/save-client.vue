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

    <el-form-item prop="client">
      <el-input
        ref="client"
        v-model="client.name"
        :placeholder="$t('client.name')"
        name="clientName"
        type="text"
        tabindex="1"
        autocomplete="on"
        style="width:50%; margin-bottom:30px;"
      />
    </el-form-item>
    <el-button
        :loading="loading"
        type="primary"
        style="width:10%; margin-bottom:30px;"
        @click.native.prevent="createClient"
      >
        {{ $t('client.complete') }}
    </el-button>


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
  name: 'Client'
})
export default class extends Vue {

  private client = {
    name: ''
  }
  private otherQuery: Dictionary<string> = {}

  private createClient(){
    (this.$refs.client as ElForm).validate(async(valid: boolean) => {
      await ClientModule.Create(this.client)
      this.$router.push({
          path: 'clinet'
        }).catch(err => {
          console.warn(err)
        })
  })
  }
}



</script>

<style lang="scss" scoped>
.field-label {
  vertical-align: middle;
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
</style>