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
    <clientsPullDown
    :clientsSeq="recievedOrder.clienetsSeq"
    @clientsSeqSubmit='clienetsSeqRecive'
    />


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
import clientsPullDown from '@/views/components/clients-pulldown.vue'
@Component({
  name: 'save-recieved-order',
  components: {
    clientsPullDown,
  }
})
export default class extends Vue {
  private recievedOrder = {
    clientsSeq: '',
    companySeq: '',
    departmentSeq: '',
    details: [{}],
    quotaionSeq: '',
    recivedOrderDate: '',
    tax: '',
    total: ''
  }

  private clienetsSeqRecive(clientsSeq: any): void {
    this.recievedOrder.clientsSeq = clientsSeq
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