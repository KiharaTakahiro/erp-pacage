<template>
<div>
  <div class="app-container">
    <el-form-item
    label="取引先"
    prop="clientsSeq"
    :rules="[
        { required: true, message: '取引先を選択してください', trigger: 'change' }
      ]"
    >
      <el-select v-model="seq" filterable clearable placeholder="取引先">
        <el-option
          v-for="client in clients"
          :key="client.clientsSeq"
          :label="client.clientsName"
          :value="client.clientsSeq">
        </el-option>
      </el-select>
    </el-form-item>
  </div>
</div>
</template>

<script lang="ts">
import { Component, Vue, Prop, Emit} from 'vue-property-decorator'
import { pullDownClient } from '@/api/client'
@Component({
  name: 'clientsPullDown',
  components: {
    
  }
})
export default class extends Vue {

  clients = [{}]


  @Prop({ default: '' })
  clientsSeq!: string;

  
  get seq() {
    return this.clientsSeq
  }
  
  set seq(value) {
    this.clientsSeqSubmit(value)
  }
  
  @Emit('clientsSeqSubmit')
  clientsSeqSubmit(value: any) {
    return value
  }
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

</style>