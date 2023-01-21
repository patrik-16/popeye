<template>
  <v-app>
    <v-container class="background" fill-height>
      <v-layout align-center justify-center>
        <v-flex>
          <div class="text-lg-center">
              <h1>Popeye <br> </h1>
            <p>Your personalized workout plan</p>
            <p>Are you ready? Let's get started! </p>
            <br>
          </div>
          <div class="text-lg-center">
            <v-dialog
              v-model="dialog"
              width="500"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  outlined
                  v-bind="attrs"
                  v-on="on"
                >
                  Start Your Journey
                </v-btn> <!-- Calls to form -->
              </template>
              <v-card>
                <v-card-title class="text-h5 grey lighten-2">
                  Terms and Conditions
                </v-card-title>

                <v-card-text class="tac">
                  Our terms and conditions include health status (defined by the
                  WHO, https://www.who.int/about/governance/constitution),
                  regional GPDR and Age (between 16 and 50). <br>
                  By accepting our terms and conditions, you confirm, that you are
                  in a healthy condition and will only use the program under healthy (defined by the WHO) circumstances.
                </v-card-text>

                <v-divider></v-divider>

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn
                    color="primary"
                    text
                    @click="toOnePage"
                  >
                    I accept
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </div>
        </v-flex>
      </v-layout>
    </v-container>
  </v-app>

</template>

<script>

export default {
  name: 'LandingPage',
  data () {
    return {
      dialog: ''
    }
  },
  methods: {
    /**
     * Uses VueRouter to send the user to the termsAndConditions.vue
     */
    toOnePage () {
      this.$router.push('/onePage')
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
.tac {
  margin-top: 3%;
}

</style>
