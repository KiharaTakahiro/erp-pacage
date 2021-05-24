<template>
  <div class="app-container">
    <div>取引先管理</div>
    <el-button
        :loading="loading"
        type="primary"
        style="width:20%; margin-bottom:30px; margin-top:30px;"
        @click.native.prevent="handleLogin"
      >
        {{ $t('clients.add') }}
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