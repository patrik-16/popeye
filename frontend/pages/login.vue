<template>
  <v-container fill height fluid>
    <v-layout row wrap align-center>
      <v-flex class="flex" xs12 sm6 offset-sm3>
        <v-card class="card">
          <v-card-text>
            <v-container>
              <v-form class="form" v-model="valid">
                <v-layout row>
                  <v-flex xs12>
                    <v-text-field
                      name="username"
                      v-model="username"
                      label="Username"
                      id="username"
                      type="username"
                      required
                    >
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
                      required
                    >
                    </v-text-field>
                  </v-flex>
                </v-layout>
                <v-layout row>
                  <v-flex xs12>
                    <v-btn class="loginButton" outlined @click="toProfile">
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
  },
  methods: {
    toProfile () {
      this.$router.push('/profile')
    },
    onLogin () {
      // Vuex?? Kommunikation mit Datenbank usw.
      // console.log({username: this.username, password: this.password})
    }
  }
}
</script>
<style scoped>
.card {
  padding: 3rem 2rem 3rem;
  border-radius: 10px;
}

.form {
  padding: 2rem 0;
}

.loginButton {
  font-family: monospace, sans-serif;
  border-radius: 7px;
  padding: 10px 10px 10px 10px;
  width: 100%;
  font-size: large;
  box-shadow: 3px 2px 2px;
  margin-bottom: 10px;
  background-color: -moz-buttondefault;
}

#username {
  font-weight: unset;
  font-size: x-large;
  margin-bottom: 2rem;
}

#password {
  font-weight: unset;
  font-size: x-large;
  margin-bottom: 2rem;
}
</style>
