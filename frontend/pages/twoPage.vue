<template>
  <div class="text--primary">
    <div>
      <v-card-text class="pink">
        <h1>
        How long is your training experience?
        </h1>
      </v-card-text>
    </div>
    <div class="my-6" />
    <!-- Using the elevation prop -->
    <v-hover>
      <template #default="{ hover }">
        <v-card
          :elevation="hover ? 24 : 6"
          class="mx-auto pa-6"
          @click="() => submit(0)"
        >
          Less than 1 year
        </v-card>
      </template>
    </v-hover>

    <div class="my-6" />

    <!-- Using a dynamic class -->
    <v-hover>
      <template #default="{ hover }">
        <div
          :class="`elevation-${hover ? 24 : 6}`"
          class="mx-auto pa-6 transition-swing"
          @click="() => submit(1)"
        >
          More than 1 year
        </div>
      </template>
    </v-hover>
  </div>
</template>

<script>
export default {
  name: 'TwoPage',
  methods: {
    toLanding () {
      this.$router.push('/')
    },
    submit (answer) {
      if (answer === 0) {
        this.$data.level = 'beginner'
      } else if (answer === 1) {
        this.$data.level = 'advanced'
      }
    }
  },
  data: () => ({
    level: ''
  })
}
</script>

<style scoped>

</style>
