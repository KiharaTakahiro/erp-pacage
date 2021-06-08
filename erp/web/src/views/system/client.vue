<template>
  <div class="app-container">
    <div>{{ $t("route.client") }}</div>
    <el-button
        :loading="loading"
        type="primary"
        style="width:13%; margin-bottom:30px; margin-top:30px;"
        @click.native.prevent="onClickButton"
      >
        {{ $t('client.add') }}
      </el-button>
        <el-button
        :loading="loading"
        type="primary"
        style="width:13%; margin-bottom:30px; margin-top:30px;"
        @click.native.prevent="onClickButton2"
      >
        {{ $t('client.edit') }}
      </el-button>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'
import '@/assets/custom-theme/index.css'



@Component({
  name: 'Client'
})
export default class extends Vue {
  onClickButton() {
    // ボタンが押されたときの処理
    this.$router.push({
    path:'save-client'
    }).catch(err => {
      console.warn(err)
    })
    
  }
  onClickButton2() {
    // ボタンが押されたときの処理
    this.$router.push({
    path:'edit-client'
    }).catch(err => {
      console.warn(err)
    })
    
  }
}
</script>

<style lang="scss" scoped>
.field-label {
  vertical-align: middle;
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
</style>