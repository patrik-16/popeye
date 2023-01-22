<template>
  <v-data-table
    :headers="headers"
    :items="exercises"
    sort-by="day"
    class="elevation-1"
    group-by="day"
    show-group-by
  >

  </v-data-table>

</template>

<script>
export default {
  name: 'ExerciseTable',
  // eslint-disable-next-line vue/require-prop-types
  props: ['programJSON'],
  data () {
    return {
      headers: [
        {
          text: 'Your Program',
          align: 'start',
          sortable: false,
          value: 'name'
        },
        { text: 'Name', value: 'name' },
        { text: 'Reps', value: 'reps' },
        { text: 'Intensiveness', value: 'intensiveness' },
        { text: 'Rest', value: 'rest' },
        { text: 'Sets', value: 'sets' }
      ],
      exercises: [
        {
          name: 'Bulgarian Split Squats',
          reps: 9,
          intensiveness: 6.0,
          rest: 24,
          sets: 4.0
        },
        {
          name: 'High Bar Squat',
          reps: 9,
          intensiveness: 6.0,
          rest: 24,
          sets: 4.0
        },
        {
          name: 'Leg Press',
          reps: 9,
          intensiveness: 6.0,
          rest: 24,
          sets: 4.0
        },
        {
          name: 'Machine Shoulder Press',
          reps: 9,
          intensiveness: 6.0,
          rest: 24,
          sets: 4.0
        },
        {
          name: 'Smith Machine Incline Bench Press',
          reps: 9,
          intensiveness: 6.0,
          rest: 24,
          sets: 4.0
        }
      ]
    }
  }
}
</script>

<style scoped>

</style>
