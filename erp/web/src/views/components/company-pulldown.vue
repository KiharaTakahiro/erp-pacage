<template>
  <div class="app-container">
    <el-form-item 
    label="会社"
    prop="companySeq"
    :rules="[
        { required: true, message: '会社を選択してください', trigger: 'change' }
      ]"
    >
      <el-select v-model="companySeq" filterable clearable v-on:change="submit" placeholder="取引先">
        <el-option
          v-for="company in companys"
          :key="company.companySeq"
          :label="company.companyName"
          :value="company.companySeq">
        </el-option>
      </el-select>
    </el-form-item>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop, Emit} from 'vue-property-decorator'
import { pullDownCompany } from '@/api/company'
@Component({
  name: 'companyPullDown',
  components: {
    
  }
})
export default class extends Vue {

  companys = [{}]


  @Prop({ default: '' })
  companySeq!: string;

  @Emit('companySeqSubmit')
  submit() {
    return this.companySeq
  }

  created() {
    this.getList()
  }

  private async getList() {
    const { data } = await pullDownCompany()
    this.companys = data.companys
    console.log(data)
  }

}



</script>

<style lang="scss" scoped>

</style>