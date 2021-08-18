<template>
  <div class="app-container">
    <div>{{ $t("route.newRecievedOrder") }}</div>
    <br>
    <br>
    <el-form
      ref="recievedOrderr"
      :model="recievedOrder"
      autocomplete="on"
      label-position="left"
    >
      <el-select v-model="recievedOrder.clienetsSeq" filterable clearable  placeholder="取引先">
        <el-option
          v-for="client in clients"
          :key="client.clientsSeq"
          :label="client.clientsName"
          :value="client.clientsSeq">
        </el-option>
      </el-select>


      <div class="complete-btn">
        <el-button
            type="primary"
            style="width:100%;"
            @click.native.prevent=""
          >
            {{ $t('recievedOrder.complete') }}
        </el-button>
      </div>
    </el-form>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { pullDownClient } from '@/api/client'
@Component({
  name: 'save-recieved-order',
  components: {
    
  }
})
export default class extends Vue {
  private recievedOrder = {
    clienetsSeq: '',
    companySeq: '',
    departmentSeq: '',
    details: [{}],
    quotaionSeq: '',
    recivedOrderDate: '',
    tax: '',
    total: ''
  }

  private clients = [{}]

  created() {
    this.getList()
  }

  private async getList() {
    const { data } = await pullDownClient()
    this.clients = data.clients
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