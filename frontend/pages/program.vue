<template>
  <v-app>
    <div>
      <h1>Page for the last question</h1>
      <v-btn outlined @click="downloadPDF()">download pdf</v-btn>
    </div>
  </v-app>
</template>

<script>

export default {
  name: 'Program',

  data: () => ({
    pdf: ''
  }),

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
        link.setAttribute('download', 'Program')
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
