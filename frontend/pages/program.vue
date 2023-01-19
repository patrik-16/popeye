<template>
  <v-card
    flat
    color="transparent"
  >
    <v-card-title>
      <h1> Your Program </h1>
    </v-card-title>
    <v-card>
      program
    </v-card>
    <v-subheader>Min and max default slider</v-subheader>

    <v-card-text>
      <v-row>
        <v-col class="pr-4">
          <v-slider
            v-model="slider"
            class="align-center"
            :max="max"
            :min="min"
            hide-details
          >
            <template v-slot:append>
              <v-text-field
                v-model="slider"
                class="mt-0 pt-0"
                hide-details
                single-line
                type="number"
                style="width: 60px"
              ></v-text-field>
            </template>
          </v-slider>
        </v-col>
      </v-row>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  name: 'Program',
  data () {
    return {
      min: -50,
      max: 90,
      slider: 40
    }
  },
  methods: {
    /**
     * Uses VueRouter to send the user to the formPage.vue
     */
    toForm () {
      this.$router.push('/program')
    }
  }
}
</script>

<style scoped>

</style>
