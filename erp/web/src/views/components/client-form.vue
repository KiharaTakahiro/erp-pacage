<template>
  <company-name
    :companyName="companyName"
    @conpanyNameValue='conpanyName'
  />
</template>
<script lang="ts">
import { Component, Vue, Watch, Prop } from 'vue-property-decorator'
import CompanyName from '@/views/components/company-name.vue'
@Component({
  name: "ClientForm",
  components: {
    CompanyName
  }
})
export default class extends Vue{
  @Prop({ default: '' })
  companyName!: string;




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