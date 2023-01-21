<template>
  <v-app class="app">
    <v-container class="pa-4 text-center">
      <v-row>
        <v-card-text
          class="title"
        >
          <h1>What do you want to focus on?</h1>
          <div><br></div>
          <h3 class="mt-1 ">Click on one, two or three areas (zB)</h3>
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
                </v-img>
              </v-card>
            </v-hover>
          </v-col>
        </template>
      </v-row>
      <v-row>
        <v-card class="anzeige">
          <v-card-title>
            You have chosen {{ priorities }}
          </v-card-title>
        </v-card>
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
        this.$data.priorities = 'Quad'
      } else if (i === 1) {
        this.$data.priorities = 'Chest'
      } else if (i === 2) {
        this.$data.priorities = 'Bicep'
      } else if (i === 3) {
        this.$data.priorities = 'Frontdelt'
      } else if (i === 4) {
        this.$data.priorities = 'Lats'
      }
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
        img: '/arms.jpg'
      },
      {
        title: 'Bicep',
        img: '/back.jpg'
      },
      {
        title: 'Frontdelt',
        img: '/chest.jpg'
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

.anzeige {
  margin: 50px;
}

.title {
}
</style>
