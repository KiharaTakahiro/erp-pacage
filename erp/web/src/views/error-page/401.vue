<template>
<div class="errPage-container">
  <div>401エラーが発生しました</div>
    <el-button
        icon="el-icon-arrow-left"
        class="back-btn"
        @click="back">
        戻る
    </el-button>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
@Component({
  name: 'Page401'
})
export default class extends Vue {
  private back() {
    if (this.$route.query.noGoBack) {
      this.$router.push({ path: '/dashboard' }).catch(err => {
        console.warn(err)
      })
    } else {
      this.$router.go(-1)
    }
  }
}
</script>

<style lang="scss" scoped>
</style>