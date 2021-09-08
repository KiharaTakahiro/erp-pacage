<template>
  <div class="app-container">
    <div>{{ $t('route.newRecievedOrder') }}</div>
    <br />
    <div class="app-container">見積番号：{{ recivedOrder.quotationSeq }}</div>
    <br />
    <el-form
      ref="recivedOrder"
      :model="recivedOrder"
      autocomplete="on"
      label-position="left"
      label-width="80px"
    >
    <clients-pull-down
      :clientsSeq.sync="recivedOrder.clientsSeq"
    />
    <company-pull-down
      :companySeq.sync="recivedOrder.companySeq"
      :departmentSeq.sync="recivedOrder.departmentSeq"
      @resetDepart="resetDepart"
    />
    <date-form
      label="受注日"
      :date.sync="recivedOrder.recivedOrderDate"
    />

      <div>{{ $t('route.OrderDetail') }}</div>
      

      <product-detail
        v-for="(detail, index) in recivedOrder.details"
        :key="index"
        :detailKey="index"
        :productSeq.sync="detail.productSeq"
        :quantity.sync="detail.quantity"
        :discount.sync="detail.discount"
        :status.sync="detail.status"
        :date.sync="detail.deriveryDate"
        @clickMinusBtn="minusBtnClick"
        
      />
      <div class="add-btn">
        <el-button
          type="success"
          icon="el-icon-plus"
          @click.native.prevent="addBtnClick"
        >
        詳細追加
        </el-button>
      </div>
      <div class="complete-btn">
        <el-button
          type="primary"
          style="width:100%;"
          @click.native.prevent="submit"
        >
          {{ $t('recivedOrder.complete') }}
        </el-button>
      </div>
    </el-form>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { Form as ElForm } from 'element-ui'
import clientsPullDown from '@/views/components/clients-pulldown.vue'
import companyPullDown from '@/views/components/company-pulldown.vue'
import productDetail from '@/views/components/product-detail.vue'
import { RecievedOrderModule } from '@/store/modules/recived-order'
import DateForm from '@/views/components/date-form.vue'
@Component({
  name: 'save-recived-order',
  components: {
    clientsPullDown,
    companyPullDown,
    productDetail,
    DateForm
  }
})
export default class extends Vue {
  // フォームのモデル
  private recivedOrder = {
    clientsSeq: RecievedOrderModule.clientsSeq,
    companySeq: RecievedOrderModule.companySeq,
    departmentSeq: RecievedOrderModule.departmentSeq,
    details: RecievedOrderModule.details,
    quotationSeq: RecievedOrderModule.quotationSeq,
    recivedOrderDate: RecievedOrderModule.recivedOrderDate,
    tax: RecievedOrderModule.tax,
    total: RecievedOrderModule.total
  }

  beforeCreate() {
    // 初期表示時にはモデルをリセットする
    RecievedOrderModule.reset()
  }

  // 作成時（仮）
  created() {
    //TODO: 見積処理を作成し、その情報をもとに作る際に消去すべし
    RecievedOrderModule.setQuotationId(2)
    // 初期表示時にpushする
    RecievedOrderModule.pushDetail({
      productSeq: '',
      quantity: '',
      discount: 0,
      deriveryDate: '',
      lotSeq: 2, //仮
      status: ''
  })
  }

  // 登録処理
  private submit() {
    (this.$refs.recivedOrder as ElForm).validate(async (valid: boolean) => {
      console.log(this.recivedOrder)
      if (valid) {
        await RecievedOrderModule.createReciverdOrder(this.recivedOrder)
        this.$router
          .push({
            path: 'recived-order'
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

  resetDepart(){
    RecievedOrderModule.setDepartmentId('')
    this.recivedOrder.departmentSeq = RecievedOrderModule.departmentSeq
  }

  minusBtnClick(key: number) {
    RecievedOrderModule.removeDetails(key)
  }

  addBtnClick() {
    RecievedOrderModule.pushDetail({
        productSeq: '',
        quantity: '',
        discount: 0,
        deriveryDate: '',
        lotSeq: 2, //仮
        status: ''    
    })
  }
}
</script>

<style lang="scss" scoped>
.field-label {
  vertical-align: middle;
}

.app-container {
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

.complete-btn {
  float: right;
}
.add-btn{
  float: left;
}
</style>
