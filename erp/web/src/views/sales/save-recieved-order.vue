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
    <div>{{ $t("route.OrderDetail") }}</div>
    <product-detail/>


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
import { RecievedOrderModule } from '@/store/modules/recieved-order'
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
    clientsSeq: RecievedOrderModule.clientId,
    companySeq: RecievedOrderModule.companyId,
    departmentSeq: RecievedOrderModule.departmentId,
    detail: RecievedOrderModule.details,
    quotationSeq: RecievedOrderModule.quotationId,
    RecievedOrderDate: RecievedOrderModule.recievedOrderDate,
    tax: RecievedOrderModule.tax,
    total: RecievedOrderModule.total
  }

  private clienetsSeqRecive(clientsSeq: any): void {
    RecievedOrderModule.setClientsID(clientsSeq)
    this.recievedOrder.clientsSeq = RecievedOrderModule.clientId
  }

  private companySeqRecive(companySeq: any): void {
    RecievedOrderModule.setDepartmentId('')
    this.recievedOrder.departmentSeq = RecievedOrderModule.departmentId
    RecievedOrderModule.setCompanyId(companySeq)
    this.recievedOrder.companySeq = RecievedOrderModule.companyId
  }

  private departmentSeqRecive(departmentSeq: any): void {
    RecievedOrderModule.setDepartmentId(departmentSeq)
    this.recievedOrder.departmentSeq = RecievedOrderModule.departmentId
  }


  private  checkBtn() {
    console.log(this.recievedOrder)
  }

}


</script>

<style lang="scss" scoped>
.field-label {
  vertical-align: middle;
}

.app-container{
  width: 100%;
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