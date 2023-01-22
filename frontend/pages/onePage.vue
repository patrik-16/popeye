<template>
  <v-container class="pa-lg-15 text-center">
    <v-row>
      <v-card class="mx-auto my-12" width="40rem">
        <v-spacer />
        <v-card-title class="justify-center">
          What's your age?
        </v-card-title>

        <v-card-text
          elevation="2"
        >
          <v-form
            ref="form"
            v-model="valid"
            class="form"
            lazy-validation
            @submit.prevent="submit"
          >
            <div>
              <v-text-field
                v-model.number="age"
                class="pa-4 mt-10 text-center"
                :rules="inputRules"
                hide-details="auto"
                label="Type in your age"
                dense
                required
                min="16"
                max="50"
              >
                {{ age }}
              </v-text-field>
            </div>
            <v-btn
              type="submit"
              :disabled="!valid"
              class="btn btn-info"
              depressed
              outlined
              x-large
              color="indigo darken-2"
              @click="toTwoPage"
            >
              Next
            </v-btn>
          </v-form>
        </v-card-text>
      </v-card>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'OnePage',
  data: () => ({
    valid: true,
    age: '',
    inputRules: [
      value => !!value || 'Age is required',
      value => (value <= 50 && value >= 16) || 'You must be between 16 and 50 years old!'
    ]
  }),
  methods: {
    submit () {
      this.$emit('submit', this.$data)
    },
    toTwoPage () {
      localStorage.setItem('age', this.$data.age)
      if (this.$refs.form.validate()) {
        this.$router.replace('/twoPage')
      }
    }
  }

}
</script>

<style scoped>

.btn {
  margin-top: 1.5rem;
  margin-bottom: 2rem;
}

</style>
