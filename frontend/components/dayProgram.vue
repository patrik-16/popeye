<template>
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
</template>

<script>

export default {
  name: 'createdProgram',
  props: ['programJSON']
}
</script>

<style scoped>

</style>
