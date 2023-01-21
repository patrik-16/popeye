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
    <!--Slavica Part-->
    <div>
      <p>age: {{ formDataObject.age }} experience: {{ formDataObject.experience }} gaol:{{ formDataObject.goal }} prio:{{ formDataObject.priorities }} dayspW: {{ formDataObject.daysPerWeek }} timepD:{{ formDataObject.timePerDay }}</p>
      <one-page v-if="currentPage === 'one'" @submit="pageOneSubmit" />
      <two-page v-if="currentPage === 'two'" @submit="pageTwoSubmit" />
      <three-page v-if="currentPage === 'three'" @submit="pageThreeSubmit" />
      <three-plus-page v-if="currentPage === 'threeplus'" @submit="pageThreePlusSubmit" />
      <four-page v-if="currentPage === 'four'" @submit="pageFourSubmit" />
      <five-page v-if="currentPage === 'five'" @goTo="pageFiveSubmit" />
    </div>
  </v-card>
</template>

<script>
import OnePage from '@/pages/onePage.vue'
import TwoPage from '@/pages/twoPage.vue'
import ThreePage from '@/pages/threePage.vue'
import ThreePlusPage from '@/pages/threePlusPage.vue'
import FourPage from '@/pages/fourPage.vue'
import FivePage from '@/pages/fivePage.vue'

export default {
  name: 'Program',
  components: { OnePage, TwoPage, ThreePage, ThreePlusPage, FourPage, FivePage },
  data () {
    return {
      min: -50,
      max: 90,
      slider: 40,
      pdf: '',
      currentPage: '',
      formDataObject: {
        age: '',
        experience: '',
        goal: '',
        priorities: '',
        daysPerWeek: '',
        timePerDay: ''
      }
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
    },
    toLanding () {
      this.$router.push('/')
    },
    pageOneSubmit (data) {
      this.$data.formDataObject.age = OnePage.data().age
      this.$data.currentPage = 'one'
    },
    pageTwoSubmit (data) {
      this.$data.formDataObject.experience = TwoPage.data().experience
      this.$data.currentPage = 'two'
    },
    pageThreeSubmit (data) {
      this.$data.formDataObject.goal = ThreePage.data().goal
      this.$data.currentPage = 'three'
    },
    pageThreePlusSubmit (data) {
      this.$data.formDataObject.priorities = ThreePlusPage.data().priorities
      this.$data.currentPage = 'threeplus'
    },
    pageFourSubmit (data) {
      this.$data.formDataObject.daysPerWeek = FourPage.data().daysPerWeek
      this.$data.currentPage = 'four'
    },
    pageFiveSubmit (data) {
      this.$data.formDataObject.timePerDay = FivePage.data().timePerDay
      this.$data.currentPage = 'five'
    }
  }
}
</script>

<style scoped>

</style>
