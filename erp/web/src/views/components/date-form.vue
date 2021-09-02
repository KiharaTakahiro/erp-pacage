<template>
  <el-form-item
    v-bind:label="label"
    >
    <el-date-picker
      v-model="dateVal"
      type="date"
      v-bind:placeholder="label">
    </el-date-picker>
  </el-form-item>
</template>

<script lang='ts'>
import { Component, Vue, Prop, Emit } from 'vue-property-decorator'
import '@/assets/custom-theme/index.css'

@Component({
  name: 'dateForm'
})

export default class extends Vue{
  @Prop({ default: '' })
  date!: string;

  @Prop({ default: '日付'})
  label!: string;

  
  get dateVal() {
    return this.date
  }

  set dateVal(value) {
    this.dateEmit(value)
  }
  @Emit('dateSubmit')
  dateEmit(value: any){
    return value
  }
}


</script>
