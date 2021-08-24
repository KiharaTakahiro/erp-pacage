<template>
  <div>
    <div class="app-container">
      <el-form-item 
      label="会社"
      prop="companySeq"
      :rules="[
          { required: true, message: '会社を選択してください', trigger: 'change' }
        ]"
      >
        <el-select v-model="companySeq" filterable clearable v-on:change="companySubmit" placeholder="取引先">
          <el-option
            v-for="company in companys"
            :key="company.companySeq"
            :label="company.companyName"
            :value="company.companySeq">
          </el-option>
        </el-select>
      </el-form-item>
    </div>
    <div class="app-container">
      <el-form-item 
      label="部署"
      prop="departmentSeq"
      :rules="[
          { required: true, message: '部署を選択してください', trigger: 'change' }
        ]"
      >
        <el-select v-model="departmentSeq" filterable clearable v-on:change="departmentSubmit" placeholder="取引先">
          <el-option
            v-for="department in departments"
            :key="department.departmentSeq"
            :label="department.departmentName"
            :value="department.departmentSeq">
          </el-option>
        </el-select>
      </el-form-item>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop, Emit} from 'vue-property-decorator'
import { pullDownCompany, pullDownDepartment } from '@/api/company'
@Component({
  name: 'companyPullDown',
  components: {
    
  }
})
export default class extends Vue {

  companys = [{}]
  departments = [{}]

  
  created() {
    this.getList()
  }

  private async getList() {
    const { data } = await pullDownCompany()
    this.companys = data.companys
  }

  private async checkDepartment(companySeq: any) {
    const { data } = await pullDownDepartment({companySeq: companySeq})
    this.departments = data.departments
  }



  @Prop({ default: '' })
  companySeq!: string;

  @Prop({ default: '' })
  departmentSeq!: string;

  @Emit('companySeqSubmit')
  companySubmit(companySeq: any) {
    this.checkDepartment(companySeq)
    return this.companySeq
  }

  @Emit('departmentSeqSubmit')
  departmentSubmit() {
    return this.departmentSeq
  }

}



</script>

<style lang="scss" scoped>

</style>