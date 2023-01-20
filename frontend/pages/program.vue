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
    <v-btn outlined @click="downloadPDF()">
      download pdf
    </v-btn>
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
      slider: 40,
      pdf: ''
    }
  },
  methods: {
    /**
     * Uses VueRouter to send the user to the formPage.vue
     */
    toForm () {
      this.$router.push('/program')
    },
    async downloadPDF () {
      try {
        const response = await fetch('http://localhost:8080/pdf/generate')
        const blob = await response.blob()
        const newBlob = new Blob([blob])
        const blobUrl = window.URL.createObjectURL(newBlob)
        const link = document.createElement('a')
        link.href = blobUrl
        link.setAttribute('download', 'Program.pdf')
        document.body.appendChild(link)
        link.click()
        link.parentNode.removeChild(link)

        // clean up Url
        window.URL.revokeObjectURL(blobUrl)
      } catch (e) {
        console.log(e)
      }
    }
  }
}
</script>

<style scoped>

</style>
