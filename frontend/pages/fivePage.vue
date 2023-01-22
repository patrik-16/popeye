<template>
  <v-container class="upperContainer pa-4 text-center">
    <v-app-bar-title class="text-sm-subtitle-1">
      <h2>How much time per day do you have?</h2>
      <div><br></div>
      <br>
    </v-app-bar-title>
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
              color="black"
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
    <v-row>
      <div><br></div>
    </v-row>
    <v-row
      class="fill-height mt-10"
      align="center"
      justify="center"
    >
      <v-hover>
        <v-card
          :elevation="hover ? 12 : 4"
          :class="{ 'on-hover': hover }"
          color="outlined"
        >
          <v-card-title
            @click="goTo"
          >
            Get your program now!
          </v-card-title>
          <v-card-subtitle>
            You have chosen {{ daysPerWeek }} days
          </v-card-subtitle>
        </v-card>
      </v-hover>
    </v-row>
  </v-container>
</template>

<script>
import program from '@/pages/program.vue'

export default {
  name: 'FivePage',
  methods: {
    submit (i) {
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
    },
    goTo () {
      this.$router.push('/program')
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

.upperContainer {
  margin-top: 6rem;
}
</style>
