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
    <clients-pull-down
      :clientsSeq="recievedOrder.clientsSeq"
      @clientsSeqSubmit="clienetsSeqRecive"
      />
    <company-pull-down
      :companySeq="recievedOrder.companySeq"
      :departmentSeq="recievedOrder.departmentSeq"
      @companySeqSubmit="companySeqRecive"
      @departmentSeqSubmit="departmentSeqRecive"
    />


      <div class="complete-btn">
        <el-button
            type="primary"
            style="width:100%;"
            @click.native.prevent="checkBtn"
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
import companyPullDown from '@/views/components/company-pulldown.vue'
import productDetail from '@/views/components/product-detail.vue'
@Component({
  name: 'save-recieved-order',
  components: {
    clientsPullDown,
    companyPullDown,
    productDetail,
  }
})
export default class extends Vue {
  private recievedOrder = {
    clientsSeq: '',
    companySeq: '',
    departmentSeq: '',
    productSeq: '',
    quantity: '',
    quotaionSeq: '',
    recivedOrderDate: '',
    tax: '',
    total: '',
    detail: []
  }

  private clienetsSeqRecive(clientsSeq: any): void {
    this.recievedOrder.clientsSeq = clientsSeq
  }

  private companySeqRecive(companySeq: any): void {
    this.recievedOrder.departmentSeq = ''
    this.recievedOrder.companySeq = companySeq
  }

  private departmentSeqRecive(departmentSeq: any): void {
    this.recievedOrder.departmentSeq = departmentSeq
  }


  private  checkBtn() {
    
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