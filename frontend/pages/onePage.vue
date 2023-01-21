<template>
  <v-container class="pa-4 text-center">
    <v-row>
      <v-card-text>
        <h2>What's your age?</h2>

        <v-card
          elevation="2"
        >
          <form
            class="form"
            @submit.prevent="checkAge"
          >
            <div>
              <v-text-field
                v-model="age"
                class="pa-4 mt-10 text-center"
                :rules="rules"
                hide-details="auto"
                label="Type in your age"
                solo
                dense
              >
                {{ age }}
              </v-text-field>
            </div>
            <div class="align-self-center mt-3">
              <v-btn
                type="submit"
                :disabled="false"
                class="btn btn-info"
                @click="toTwoPage"
              >
                Next
              </v-btn>
            </div>
          </form>
        </v-card>
      </v-card-text>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'OnePage',
  data: () => ({
    rules: [
      value => (value <= 50 && value >= 18) || 'You must be between 18 and 50 years old!'
    ],
    valid: false,
    age: ''
  }),
  methods: {
    checkAge () {
      this.age = this.age >= 18 && this.age <= 50
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
