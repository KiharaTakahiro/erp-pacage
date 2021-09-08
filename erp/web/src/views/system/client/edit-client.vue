<template>
  <div class="app-container">
    <div>{{ $t('route.editClient') }}</div>
    <br />
    <br />
    <el-form
      ref="client"
      :model="client"
      autocomplete="on"
      label-position="left"
    >
      <company-name :companyName.sync="client.name" />
      <div class="complete-btn">
        <el-button
          type="primary"
          style="width:100%;"
          @click.native.prevent="updateClient"
        >
          {{ $t('client.complete') }}
        </el-button>
      </div>
    </el-form>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { Form as ElForm } from 'element-ui'
import { ClientModule } from '@/store/modules/client'
import CompanyName from '@/views/components/company-name.vue'
import '@/assets/custom-theme/index.css'

@Component({
  name: 'Client-save',
  components: {
    CompanyName
  }
})
export default class extends Vue {
  client = {
    id: ClientModule.id,
    name: ClientModule.name
  }

  private updateClient() {
    (this.$refs.client as ElForm).validate(async (valid: boolean) => {
      if (valid) {
        await ClientModule.UpdateClient(this.client)
        this.$router
          .push({
            path: 'client-list'
          })
          .catch(err => {
            console.warn(err)
          })
        this.$message({
          message: this.$t('components.createClients').toString(),
          type: 'success'
        })
      } else {
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

.app-container {
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

.complete-btn {
  float: right;
}
</style>
