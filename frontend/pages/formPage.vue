<template>
  <v-app>
    <h1>Hello Form ... test</h1>

    <v-spacer />
    <v-btn outlined @click="toLanding"> Back to landing page .... </v-btn> <!-- Calls to form -->
    <!-- Design your buttons: https://vuetifyjs.com/en/components/buttons/#loaders -->
    <v-spacer />
  </v-app>
</template>

<script>
export default {
  name: 'formPage',
  methods: {
    /**
     * Uses VueRouter to send the user back to index.vue
     */
    toLanding () {
      this.$router.push('/')
    }
  }
}
</script>

<style scoped>

</style>
