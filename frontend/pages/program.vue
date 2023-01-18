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
        const response = await fetch('./pdf/generate')
        const url = window.URL.createObjectURL(new Blob([response.data]))
        const link = document.createElement('a')
        link.href = url
        link.setAttribute('downlaod', response.headers.get())
      } catch (e) {
        console.log(e)
      }
      console.log(this.pdf)
    }
  }
}
</script>

<style scoped>

</style>
