<template>
  <v-container class="upperContainer pa-4 text-center">
    <v-app-bar-title class="text-sm-subtitle-1">
      <h2>
        How many days per week can you workout?
      </h2>
      <br>
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
                  v-model="daysPerWeek"
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
                        {{ item.icon }}
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
    <v-row
      class="fill-height"
      align="center"
      justify="center"
    >
      <div>
        <br>
      </div>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'FourPage',
  methods: {
    submit (i) {
      this.$router.push('/fivepage')
      if (i === 0) {
        this.$data.daysPerWeek = '2'
      } else if (i === 1) {
        this.$data.daysPerWeek = '3'
      } else if (i === 2) {
        this.$data.daysPerWeek = '4'
      } else if (i === 3) {
        this.$data.daysPerWeek = '5'
      }
      localStorage.setItem('daysPerWeek', this.$data.daysPerWeek)
    }
  },
  data: () => ({
    daysPerWeek: '',
    icons: ['mdi-weight-lifter'],
    items: [
      { title: '2' },
      { title: '3' },
      { title: '4' },
      { title: '5' }
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
