<template>
  <v-app>
    <v-container>
    <v-spacer />
      <v-btn outlined @click="toLanding"> Back to landing page .... </v-btn> <!-- Calls to form -->
      <!-- Design your buttons: https://vuetifyjs.com/en/components/buttons/#loaders -->
    <v-spacer />
    </v-container>
  </v-app>
</template>

<script>
export default {
  name: 'FormPage',
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
