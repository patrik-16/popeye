<template>
  <v-container class="pa-4 text-center">
    <v-row>
      <v-card-text
        class="title"
      >
        <h1 class="pa-4">
          How much time per day do you have?
        </h1>
        <div><br></div>
      </v-card-text>
    </v-row>
    <v-row
      class="fill-height"
      align="center"
      justify="center"
    >
      <template
        v-for="(item, i) in items"
      >
        <v-col
          :key="i"
          cols="12"
          md="3"
        >
          <v-hover v-slot="{ hover }">
            <v-card
              :elevation="hover ? 12 : 2"
              :class="{ 'on-hover': hover }"
              color="grey"
              @click="submit(i)"
            >
              <v-card-title class="text-h4 white--text">
                <v-row
                  class="fill-height flex-column"
                  justify="space-between"
                >
                  <p class="mt-10 subheading">
                    {{ item.title }}
                  </p>

                  <div class="align-self-center">
                    <v-btn
                      v-for="(icon, index) in icons"
                      :key="index"
                      :class="{ 'show-btns': hover }"
                      :color="transparent"
                      icon
                    >
                      <v-icon
                        :class="{ 'show-btns': hover }"
                        :color="transparent"
                      >
                        {{ icon }}
                      </v-icon>
                    </v-btn>
                  </div>
                </v-row>
              </v-card-title>
            </v-card>
          </v-hover>
        </v-col>
      </template>
    </v-row>
  </v-container>
</template>

<script>
import program from '@/pages/program.vue'

export default {
  name: 'FivePage',
  methods: {
    submit (i) {
      this.$router.push('/program')
      if (i === 0) {
        this.$data.timePerDay = 'FORTY'
      } else if (i === 1) {
        this.$data.timePerDay = 'SIXTY'
      } else if (i === 2) {
        this.$data.timePerDay = 'EIGHTY'
      } else if (i === 3) {
        this.$data.timePerDay = 'UNLIMITED'
      }
      localStorage.setItem('timePerDay', this.$data.timePerDay)
    }
  },
  data: () => ({
    timePerDay: '',
    daysPerWeek: program.data().formDataObject.daysPerWeek,
    icons: ['mdi-weight-lifter'],
    hover: '',
    items: [
      { title: '40 min' },
      { title: '60 min' },
      { title: '1h 20 min' },
      { title: 'no matter' }
    ],
    transparent: 'rgba(255, 255, 255, 0)'
  })
}
</script>

<style scoped>
.v-card {
  transition: opacity .4s ease-in-out;
}

.v-card:not(.on-hover) {
  opacity: 0.6;
}

.show-btns {
  color: rgba(255, 255, 255, 1) !important;
}

.title {
}
</style>
