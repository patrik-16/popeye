<template>
  <v-app>
    <v-container class="background" fill-height>
      <v-layout align-center justify-center>
        <div class="align-center justify-space-around pillar rounded-lg">
          <v-flex>
            <div class="text-lg-center">
              <br>
              <br>
              <h2>Popeye </h2> <br>
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
                    depressed
                    outlined
                    x-large
                    v-bind="attrs"
                    v-on="on"
                    color="indigo darken-2"
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
                    in a healthy condition and will only use the program under healthy (defined by the WHO)
                    circumstances.
                  </v-card-text>

                  <v-divider></v-divider>

                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="blue-grey darken-2"
                      text
                      @click="toLandingPage"
                    >
                      Decline
                    </v-btn>
                    <v-btn
                      color="indigo darken-2"
                      text
                      @click="toOnePage"
                    >
                      Accept
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </div>
          </v-flex>
        </div>
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
    },
    toLandingPage () {
      this.$data.dialog = false
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

.pillar {
  width: 30vw;
  height: 24rem;
  background: rgba(0, 0, 0, 0.1);
  padding: 20px;
}

</style>
