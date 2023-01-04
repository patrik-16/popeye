<template>
  <v-app>
    <v-container class="background" fill-height>
      <v-layout align-center justify-center>
        <v-flex>
          <div class="text-lg-center">
            <p>text</p>
            <p>some more text</p>
            <p>if you click start your journey the </p>
            <br>
          </div>
          <div class="text-lg-center">
            <v-btn outlined @click="toForm">Start Your Journey</v-btn> <!-- Calls to form -->
          </div>
        </v-flex>
      </v-layout>
    </v-container>
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

.background {
  min-width: 100%;
  min-height: 100vh;
  background: url("static/gymBackground.jpg") no-repeat top center fixed;
  background-size: cover;
  margin-top: -12px;
}

</style>
