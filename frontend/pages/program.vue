<template>
  <v-container class="upperContainer pa-4 text-center">
    <v-card
      flat
      color="transparent"
    >
      <v-card-title align="center" class="justify-center">
        <h2 class="distance">
          Your Program
        </h2>
      </v-card-title>
      <v-btn
        class="distance"
        outlined
        depressed
        color="indigo darken-2"
        @click="getBackendData()"
      >
        Calculate Program
      </v-btn>
      <v-card>
        <!--<dayProgram :programJSON="programJSON"></dayProgram>-->
        <div>
          <v-row
            class="fill-height"
            justify="center"
          >
            <template>
              <v-col
                v-for="day in programJSON"
                :key="day.day"
                cols="14"
                md="2"
              >
                <v-card
                  color="blue-grey lighten-4"
                >
                  <v-card-title
                    align="left"
                  >Day {{ day.day }}
                  </v-card-title>
                  <v-card-text
                    align="left"
                    v-for="exercise in day.exerciseList"
                    :key="exercise.name">
                    <h4>Exercise: {{ exercise.name }}</h4>
                    <p v-for="(value, name) in exercise.bodypartToEffectiveness" :key="name">
                      Bodypart: {{ name }}
                    </p>
                    <p>Sets: {{ exercise.sets }}</p>
                    <p>Reps: {{ exercise.reps }}</p>
                    <p>Rest: {{ exercise.rest }} seconds</p>
                    <p>Intensiveness: {{ exercise.intensiveness }}</p>
                  </v-card-text>
                </v-card>
              </v-col>
            </template>
          </v-row>
        </div>
      </v-card>
      <div class="upperContainer">
        <v-btn
          outlined
          depressed
          color="indigo darken-2"
          class="distance"
          @click="getPDFData()"
        >
          download pdf
        </v-btn>
      </div>
      <v-subheader>Do you want to adjust the amount of days?</v-subheader>

      <v-card-text>
        <v-row>
          <v-col class="pr-4">
            <v-slider
              v-model="value"
              :tick-labels="tickLabels"
              min="2"
              max="5"
              steps="1"
              ticks="always"
              tick-size="4"
            >
              Pick another amount of days
            </v-slider>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
  </v-container>
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
      programJSON: [],
      currentPage: '',
      range: [
        '2', '3', '4', '5'
      ],
      value: 0,
      dayNumer: 0,
      tickLabels: [
        2, 3, 4, 5
      ],
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
    changeDays () {
      this.$data.formDataObject.daysPerWeek = this.$data.value
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
.distance {
  margin: 2rem;
}

.upperContainer {
  margin-top: 2rem;
}
</style>
