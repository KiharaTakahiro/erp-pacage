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
    <product-detail
      :productSeq="detail.productSeq"
      @productSeqSubmit="productSeqRecive"
      :quantity="detail.quantity"
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
  // フォームのモデル
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
  // 詳細用のモデル
  private detail = {
    productSeq: '',
    quantity: ''
  }
  //取引先のエミット
  private clienetsSeqRecive(clientsSeq: any): void {
    RecievedOrderModule.setClientsID(clientsSeq)
    this.recievedOrder.clientsSeq = RecievedOrderModule.clientId
  }
  //会社のエミットっと
  private companySeqRecive(companySeq: any): void {
    //部署リセット
    RecievedOrderModule.setDepartmentId('')
    this.recievedOrder.departmentSeq = RecievedOrderModule.departmentId
    RecievedOrderModule.setCompanyId(companySeq)
    this.recievedOrder.companySeq = RecievedOrderModule.companyId
  }

  //部署のエミット
  private departmentSeqRecive(departmentSeq: any): void {
    RecievedOrderModule.setDepartmentId(departmentSeq)
    this.recievedOrder.departmentSeq = RecievedOrderModule.departmentId
  }
  
  //商品のエミット
  private productSeqRecive(productSeq: any): void {
    this.detail.productSeq = productSeq
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