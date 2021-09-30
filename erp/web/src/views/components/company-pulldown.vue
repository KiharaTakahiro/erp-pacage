<template>
  <div>
    <el-row>
      <el-col :span="5">
        <el-form-item
          label="会社"
          prop="companySeq"
          :rules="[
            {
              required: required,
              message: '会社を選択してください',
              trigger: 'change'
            }
          ]"
        >
          <el-select :value="comSeq" @input="changeCompany" @change="resetDepart" filterable clearable placeholder="会社名">
            <el-option
              v-for="company in companys"
              :key="company.companySeq"
              :label="company.companyName"
              :value="company.companySeq"
            >
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="5">
        <el-form-item
          label="部署"
          prop="departmentSeq"
          :rules="[
            {
              required: required,
              message: '部署を選択してください',
              trigger: 'change'
            }
          ]"
        >
          <el-select v-model="depSeq" filterable clearable placeholder="部署">
            <el-option
              v-for="department in departments"
              :key="department.departmentSeq"
              :label="department.departmentName"
              :value="department.departmentSeq"
            >
            </el-option>
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
  </div>
</template>

<script lang="ts">
import { Component, Vue, PropSync, Emit, Prop } from 'vue-property-decorator'
import { pullDownCompany, pullDownDepartment } from '@/api/company'
import { RecivedOrderModule } from '@/store/modules/recived-order'
@Component({
  name: 'companyPullDown',
  components: {}
})
export default class extends Vue {
  @PropSync('companySeq', { type: [String, Number] }) comSeq!: string
  @PropSync('departmentSeq', { type: [String, Number] }) depSeq!: string
  @Prop({ default: false }) required!: boolean

  companys = [{}]
  departments = [{}]

  async created() {
    this.getList()
    /**
     * 編集画面の場合初期表示で部署プルダウンが作成されない為
     * ストアに会社Seqがある場合プルダウンのAPIを投げる。
     */
    if(RecivedOrderModule.companySeq != ''){
      const { data } = await pullDownDepartment({ companySeq: RecivedOrderModule.companySeq })
      this.departments = data.departments
    }
  }

  // 会社プルダウン作成
  private async getList() {
    const { data } = await pullDownCompany()
    this.companys = data.companys
    }

  // 会社が変更された際部署をリセット
  changeCompany(value: any){
    this.comSeq = value
    this.checkDepartment(value)
  }

  // 親コンポーネントで部署リセットする為のエミット。返値は使用しない。
  @Emit('resetDepart')
  resetDepart(){
    return this.comSeq
  }

  /**
   * 部署のプルダウンの処理
   * 会社Seqが空で部署リセット
   * そうでなければ該当の会社の部署を問合せ
   * 会社と部署の不一致をなくすための処理
   */
  private async checkDepartment(companySeq: any) {
    if (companySeq === '') {
      this.departments = [{}]
    } else {
      const { data } = await pullDownDepartment({ companySeq: companySeq })
      this.departments = data.departments
    }
  }
}
</script>

<style lang="scss" scoped></style>
