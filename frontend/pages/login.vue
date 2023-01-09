<template>
  <v-container fill-height>
    <v-layout row wrap align-center>
      <v-flex xs12 sm6 offset-sm3>
        <v-card>
          <v-card-text>
            <v-container>
              <v-form type="text" v-model="valid">
                <v-layout row>
                  <v-flex xs12>
                    <v-text-field
                      name="username"
                      v-model="username"
                      label="Username"
                      id="username"
                      type="username"
                      required>
                    </v-text-field>
                  </v-flex>
                </v-layout>
                <v-layout row>
                  <v-flex xs12>
                    <v-text-field
                      name="password"
                      v-model="password"
                      label="Password"
                      id="password"
                      type="password"
                      required>
                    </v-text-field>
                </v-flex>
                </v-layout>
                <v-layout row>
                  <v-flex xs12>
                    <v-btn type="login">
                      Login
                    </v-btn>
                  </v-flex>
                </v-layout>
              </v-form>
            </v-container>
          </v-card-text>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  name: 'Login',
  data: function () {
    return {
      valid: true,
      username: '',
      password: ''
    }
  }
}
</script>

<style scoped>

</style>
