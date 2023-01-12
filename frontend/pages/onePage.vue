<template>
  <v-form v-model="valid">
    <v-container>
      <v-row
        class=""
        justify="center"
      >
        <v-col
          align-self="center"
          cols="12"
          md="4"
        >
          <v-app-bar-title>
            <h1>
              First, let's get to know you!
            </h1>
          </v-app-bar-title>
          <v-text-field
            v-model="firstname"
            :rules="nameRules"
            label="First name"
            required
          />
          <v-text-field
            v-model="lastname"
            :rules="nameRules"
            :counter="10"
            label="Last name"
            required
          />
          <v-text-field
            v-model="email"
            :rules="emailRules"
            label="E-mail"
            required
          />
        </v-col>
      </v-row>
      <v-btn
        color="blue"
        x-large
        elevation="3"
        rounded
        @click="toLanding"
      >
        Go Back
      </v-btn>
      <v-btn
        color="yellow"
        x-large
        elevation="3"
        rounded
        @click="toTwoPage"
      >
        Go To next page
      </v-btn>
    </v-container>
  </v-form>
</template>

<script>
export default {
  name: 'OnePage',
  methods: {
    toLanding () {
      this.$router.push('/')
    },
    toTwoPage () {
      this.$router.push('/twoPage')
    }
  },
  data: () => ({
    valid: false,
    firstname: '',
    lastname: '',
    nameRules: [
      v => !!v || 'Name is required',
      v => v.length <= 10 || 'Name must be less than 10 characters'
    ],
    email: '',
    emailRules: [
      v => !!v || 'E-mail is required',
      v => /.+@.+/.test(v) || 'E-mail must be valid'
    ]
  })
}
</script>

<style scoped>

</style>
