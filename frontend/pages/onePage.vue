<template>
  <v-container class="pa-4 text-center">
    <v-card>
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
          <div>
            <v-text-field
              class="pa-4 mt-10 text-center"
              v-model="age"
              label="Type in your age"
              solo
              dense
            >
              {{ age }}
            </v-text-field>
          </div>
          <div class="align-self-center mt-3">
            <v-btn
              v-show="ageValidation"
              class="btn"
              @click="toTwoPage"
            >
              Next
            </v-btn>
          </div>

        </v-card>
      </v-form>
    </v-card>
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
.btn {
  margin-bottom: 5%;
}

</style>
