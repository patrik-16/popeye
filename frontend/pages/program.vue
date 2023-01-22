<template>
  <v-card
    flat
    color="transparent"
  >
    <v-card-title>
      <h1> Your Program </h1>
    </v-card-title>
    <v-btn outlined @click="getBackendData()">
      Calculate Program
    </v-btn>
    <v-card>
      <v-card-title>Your Program</v-card-title>
      <v-card-text> {{ programJSON[0] }}</v-card-text>
      <v-card-text> {{ programJSON[1] }}</v-card-text>
      <v-card-text> {{ programJSON[2] }}</v-card-text>
      <v-card-text> {{ programJSON[3] }}</v-card-text>
      <v-card-text> {{ programJSON[4] }}</v-card-text>
      <!--<dayProgram :programJSON="programJSON"></dayProgram>-->
      <div>
        <div v-for="day in programJSON" :key="day.day">
          <h3>Day {{ day.day }}</h3>
          <div v-for="exercise in day.programJSON" :key="exercise.name">
            <p>{{ exercise.name }}</p>
            <p>Sets: {{ exercise.sets }}</p>
            <p>Reps: {{ exercise.reps }}</p>
            <p>Rest: {{ exercise.rest }} seconds</p>
            <p>Intensiveness: {{ exercise.intensiveness }}</p>
            <p>Experience: {{ exercise.experience }}</p>
            <p>Difficulty: {{ exercise.difficulty }}</p>
            <p>Body part to effectiveness: {{ exercise.bodypartToEffectiveness }}</p>
          </div>
        </div>
      </div>
    </v-card>
    <v-btn outlined @click="getPDFData()">
      download pdf
    </v-btn>
    <v-btn outlined @click="showLog()">
      showLog
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
              />
            </template>
          </v-slider>
        </v-col>
      </v-row>
    </v-card-text>
    <!--Slavica Part-->
    <div>
      <p>age: {{ formDataObject.age }} experience: {{ formDataObject.experience }} goal:{{ formDataObject.goal }} prio:{{ formDataObject.priorities }} dayspW: {{ formDataObject.daysPerWeek }} timepD:{{ formDataObject.timePerDay }}</p>
    </div>
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
      pdf: '',
      programJSON: [
      ],
      currentPage: '',
      formDataObject: {
        age: '',
        experience: '',
        goal: '',
        priorities: [],
        daysPerWeek: '',
        timePerDay: ''
      }
    }
  },
  /**
   * The mounted () method is executed when the component is loaded https://vuejs.org/api/options-lifecycle.html#mounted
   */
  mounted () {
    this.$data.formDataObject.age = localStorage.getItem('age')
    this.$data.formDataObject.experience = localStorage.getItem('experience')
    if (this.$data.formDataObject.experience === 'BEGINNER') {
      this.$data.formDataObject.goal = 'NONE'
      this.$data.formDataObject.priorities = 'NONE'
      this.$data.formDataObject.daysPerWeek = localStorage.getItem('daysPerWeek')
      this.$data.formDataObject.timePerDay = localStorage.getItem('timePerDay')
    } else if (this.$data.formDataObject.experience === 'ADVANCED') {
      this.$data.formDataObject.goal = localStorage.getItem('goal')
      this.$data.formDataObject.priorities = localStorage.getItem('priorities')
      this.$data.formDataObject.daysPerWeek = localStorage.getItem('daysPerWeek')
      this.$data.formDataObject.timePerDay = localStorage.getItem('timePerDay')
    }
  },
  methods: {
    /**
     * Uses VueRouter to send the user to the formPage.vue
     */
    toForm () {
      this.$router.push('/program')
    },
    async downloadPDF (url, input) {
      const getRouting = {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(input)
      }
      try {
        const response = await fetch(url, getRouting)
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
    async postFetch (url, input) {
      const getRouting = {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(input)
      }
      const response = await fetch(url, getRouting)
      const data = await response.json()
      return { data }
    },
    getBackendData () {
      const input = {
        age: this.$data.formDataObject.age,
        experience: this.$data.formDataObject.experience,
        priorities: [
          this.$data.formDataObject.priorities
        ],
        daysPerWeek: this.$data.formDataObject.daysPerWeek,
        timePerDay: this.$data.formDataObject.timePerDay,
        goal: this.$data.formDataObject.goal
      }
      const data = this.$data
      this.$data.programJSON = this.postFetch('http://localhost:8080/api/generateprogram', input)
        .then((result) => {
          data.programJSON = result.data.program
        })
      console.log(this.$data.programJSON)
    },
    getPDFData () {
      const input = {
        age: this.$data.formDataObject.age,
        experience: this.$data.formDataObject.experience,
        priorities: [
          this.$data.formDataObject.priorities
        ],
        daysPerWeek: this.$data.formDataObject.daysPerWeek,
        timePerDay: this.$data.formDataObject.timePerDay,
        goal: this.$data.formDataObject.goal
      }
      this.programJSON = this.downloadPDF('http://localhost:8080/pdf/generate', input)
    },
    showLog () {
      console.log(this.programJSON)
    }
  }
}
</script>

<style scoped>

</style>
