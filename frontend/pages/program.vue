<template>
  <h1>Program Page</h1>
</template>

<script>
export default {
  name: 'Program',

  methods: {
    /**
     * Uses VueRouter to send the user to the formPage.vue
     */
    toForm () {
      this.$router.push('/program')
    }
  }
}
</script>

<style scoped>

</style>
