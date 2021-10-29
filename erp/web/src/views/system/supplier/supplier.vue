<template>
  <transition
    name="fade-transform"
    mode="out-in"
  >
    <router-view />
  </transition>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'

@Component({
  name: 'supplier'
})
export default class extends Vue {}
</script>
