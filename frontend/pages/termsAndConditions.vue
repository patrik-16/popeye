<template>
  <div class="text-center">
    <v-sheet
      class="px-7 pt-7 pb-4 mx-auto text-center d-inline-block"
      color="blue-grey darken-3"
      dark
    >
      <div class="grey--text text--lighten-1 text-body-2 mb-4">
        Do you accept our terms and conditions?
      </div>

      <v-btn
        :disabled="loading"
        class="ma-1"
        color="grey"
        plain
        @click="toOnePage"
      >
        Yes
      </v-btn>

      <v-btn
        class="ma-1"
        color="error"
        plain
        @click="toLanding"
      >
        No
      </v-btn>
    </v-sheet>
  </div>
</template>

<script>

export default {
  name: 'TermsAndConditions',
  methods: {
    toLanding () {
      this.$router.push('/')
    },
    toOnePage () {
      this.$router.push('onePage')
    }
  }

}
</script>

<style scoped>

</style>
