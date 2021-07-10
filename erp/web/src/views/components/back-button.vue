<template>
  <el-button
    type="info"
    style="width:100%; margin-bottom:30px; margin-top:30px;"
    @click.native.prevent="goTop"
  >
    {{ $t('route.back') }}
  </el-button>
</template>
<script lang='ts'>
import { Component, Vue} from 'vue-property-decorator'


@Component({
  name: 'backBtn'
})

export default class extends Vue {
  goTop() {
    this.$router.push({
    path:'/'
    }).catch(err => {
      console.warn(err)
    })
  }
}
</script>
<style lang="scss">
</style>