<template>
  <v-form v-model="valid">
    <v-container>
      <v-row
        class="green rounded-pill text-no-wrap "
        justify="center"
      >
        <v-col
          align-self="center"
          cols="12"
          md="4"
        >
          <v-text-field
            v-model="firstname"
            :rules="nameRules"
            :counter="10"
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
      <v-row>
        <v-calendar class="pa-4 secondary text-no-wrap rounded-pill pink"
        >
YYY</v-calendar>
        fsfdhjdklsd
        <v-app-bar
          @click="toLanding"
        >
          sdfghjk
        </v-app-bar>
        <v-btn @click="toTwoPage"
        >
          Go To next page
        </v-btn>
        <v-avatar>
          <h1>Yo</h1>
          <textarea>Guten Tag</textarea>
        </v-avatar>
      </v-row>
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
