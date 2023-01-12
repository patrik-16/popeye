<template>
  <v-app>
    <h1>Program Page</h1>
    <div>
      <a href="/assets/my.pdf">download pdf</a>
    </div>
  </v-app>
</template>

<script>
export default {
  name: 'Program',
  data () {
    return {
      programPDF: ''
    }
  },

  methods: {
    /**
     * Uses VueRouter to send the user to the formPage.vue
     */
    toForm () {
      this.$router.push('/program')
    },
    async getData () {
      try {
        return await axios.get({
          url: '/download/currentPDFprogram.pdf',
          method: 'GET',
          responseType: 'blob'
        }).then((response) => {
          console.log(response)
          const downloadUrl = window.URL.createObjectURL(new Blob([response.data]))
          this.programPDF = downloadUrl
          this.downloadFile()
        })
      } catch (error) {
        console.log(error)
      }
    },
    downloadFile () {
      const link = document.createElement('a')
      link.href = this.programPDF
      link.setAttribute('download', 'YourProgram.pdf')
      document.body.appendChild(link)
      link.click()
      link.remove()
    }
  }
}
</script>

<style scoped>

</style>
