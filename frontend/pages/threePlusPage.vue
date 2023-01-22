<template>
  <v-app class="app">
    <v-container class="upperContainer pa-4 text-center">
      <v-app-bar-title class="text-sm-subtitle-1">
        <h2>What is your goal? </h2>
        <br>
        <h3>Click on one bodypart you want to focus on</h3>
        <div><br></div>
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
            md="4"
          >
            <v-hover v-slot="{ hover }">
              <v-card
                :elevation="hover ? 12 : 2"
                :class="{ 'on-hover': hover }"
                @click="submit(i)"
              >
                <v-img
                  :src="item.img"
                  height="225px"
                >
                  <div class="pillar">
                    <v-card-title class="text-h5 white--text">
                      <v-row
                        class="fill-height flex-column"
                        justify="space-between"
                      >
                        <p class="mt-15 subheading text-center">
                          {{ item.title }}
                        </p>

                        <div class="align-self-center text-center">
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
                  </div>
                </v-img>
              </v-card>
            </v-hover>
          </v-col>
        </template>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>

export default {
  name: 'ThreePlusPage',
  methods: {
    submit (i) {
      this.$router.push('/fourPage')
      if (i === 0) {
        this.$data.priorities = 'QUADS'
      } else if (i === 1) {
        this.$data.priorities = 'CHEST'
      } else if (i === 2) {
        this.$data.priorities = 'BICEP'
      } else if (i === 3) {
        this.$data.priorities = 'FRONTDELT'
      } else if (i === 4) {
        this.$data.priorities = 'LATS'
      }
      localStorage.setItem('priorities', this.$data.priorities)
    }
  },
  data: () => ({
    priorities: '',
    icons: ['mdi-weight-lifter'],
    items: [
      {
        title: 'Quad',
        img: '/legs.png'
      },
      {
        title: 'Chest',
        img: '/chest.jpg'
      },
      {
        title: 'Bicep',
        img: '/arms.jpg'
      },
      {
        title: 'Frontdelt',
        img: '/frontdelt1.webp.crdownload'
      },
      {
        title: 'Lats',
        img: '/glutes.webp'
      }
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

.pillar {
  background: rgba(0, 0, 0, 0.6);
  height: 15rem;
}

.upperContainer {
 margin-top: 6rem;
}
</style>
