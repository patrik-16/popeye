<template>
  <v-container class="pa-4 text-center">
    <v-form
      v-model="valid"
      class="form"
      form="required"
    >
      <v-app-bar-title
        class="title"
      >
        <h2>What's your age?</h2>
      </v-app-bar-title>
      <v-card
        elevation="2"
      >
        <v-text-field
          class='pa-4 mt-10 text-center'
          v-model="age"
          label="Type in your age"
          solo
          dense
        >
          {{ age }}
        </v-text-field>
        <v-btn
          v-show="ageValidation"
          @click="toTwoPage"
        >
          Next
        </v-btn>

      </v-card>
    </v-form>
  </v-container>
</template>

<script>
export default {
  name: 'OnePage',
  data: () => ({
    valid: false,
    age: '',
    ageValidation: true
  }),
  methods: {
    checkAge () {
      this.ageValidation = this.age >= 18 && this.age <= 50
    },
    toTwoPage () {
      this.$router.push('/twoPage')
    }
  }

}
</script>

<style scoped>
.form {
}

</style>
