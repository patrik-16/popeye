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
                  Privacy Policy
                </v-card-title>

                <v-card-text>
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
                  dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
                  aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum
                  dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui
                  officia deserunt mollit anim id est laborum.
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

</style>
