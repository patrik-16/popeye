<template>
  <div>
    <one-page v-if="currentPage === 0" @submit="pageOneSubmit"/>
    <two-page v-if="currentPage === 1" @submit="pageTwoSubmit"/>
    <three-page v-if="currentPage === 2" @submit="pageTwoSubmit"/>
  </div>
</template>

<script>
import OnePage from './onePage'
import TwoPage from './twoPage'
import ThreePage from './threePage'
export default {
  name: 'Questionaire',
  components: { ThreePage, TwoPage, OnePage },
  methods: {
    toLanding () {
      this.$router.push('/')
    },
    pageOneSubmit (data) {
      this.$data.formDataObject.email = data.email
      this.$data.formDataObject.firstname = data.firstname
      this.$data.formDataObject.lastname = data.lastname
      this.$data.currentPage++
    },
    pageTwoSubmit (data) {
      this.$data.formDataObject.level = data.level
      this.$data.currentPage++
    },
    pageThreeSubmit (data) {
      this.$data.formDataObject.goal = data.goal
      this.$data.currentPage++
    }
  },
  data: () => ({
    currentPage: 0,
    formDataObject: {
      email: '',
      level: '',
      goal: ''
    }
  })
}
</script>

<style scoped>

</style>
