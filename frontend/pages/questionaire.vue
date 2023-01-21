<template>
  <div>
    <one-page v-if="currentPage === 'one'" @submit="pageOneSubmit"/>
    <two-page v-if="currentPage === 'two'" @submit="pageTwoSubmit"/>
    <three-page v-if="currentPage === 'three'" @submit="pageThreeSubmit"/>
    <three-plus-page v-if="currentPage === 'threeplus'" @submit="pageThreePlusSubmit"/>
    <four-page v-if="currentPage === 'four'" @submit="pageFourSubmit"/>
    <five-page v-if="currentPage === 'five'" @goTo="pageFiveSubmit"/>

  </div>
</template>

<script>
import OnePage from './onePage'
import TwoPage from './twoPage'
import ThreePage from './threePage'
import ThreePlusPage from './threePlusPage'
import FourPage from './fourPage'
import FivePage from './fivePage'

export default {
  name: 'Questionaire',
  components: { OnePage, TwoPage, ThreePage, ThreePlusPage, FourPage, FivePage },
  methods: {
    toLanding () {
      this.$router.push('/')
    },
    pageOneSubmit (data) {
      this.$data.formDataObject.age = data.age
      this.$data.currentPage = 'one'
    },
    pageTwoSubmit (data) {
      this.$data.formDataObject.experience = data.experience
      this.$data.currentPage = 'two'
    },
    pageThreeSubmit (data) {
      this.$data.formDataObject.goal = data.goal
      this.$data.currentPage = 'three'
    },
    pageThreePlusSubmit (data) {
      this.$data.formDataObject.priorities = data.priorities
      this.$data.currentPage = 'threeplus'
    },
    pageFourSubmit (data) {
      this.$data.formDataObject.daysPerWeek = data.daysPerWeek
      this.$data.currentPage = 'four'
    },
    pageFiveSubmit (data) {
      this.$data.formDataObject.timePerDay = data.timePerDay
      this.$data.currentPage = 'five'
    }
  },
  data: () => ({
    currentPage: '',
    formDataObject: {
      age: '',
      experience: '',
      goal: '',
      priorities: '',
      daysPerWeek: 'x',
      timePerDay: ''
    }
  })
}
</script>

<style scoped>

</style>
