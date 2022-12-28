<template>
  <v-app>
    <v-card>
      <v-card-title>Test Index</v-card-title>
      <v-alert>Check out Vuetify!</v-alert>
      <v-card-text>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.</v-card-text>
    </v-card>

    <v-spacer />
    <v-btn outlined @click="toForm"> Get Your Programm (or... Start Your Journey Now! </v-btn> <!-- Calls to form -->
    <v-spacer />

  </v-app>

</template>

<script>
export default {
  name: 'landingPage',

  methods: {
    /**
     * Uses VueRouter to send the user to the formPage.vue
     */
    toForm () {
      this.$router.push('/formPage')
    }
  }
}
</script>

<style scoped>

</style>
