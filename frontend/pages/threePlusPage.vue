<template>
  <v-container class="pa-4 text-center">
    <v-row>
      <v-card-text
        class="title"
      >
        <h1>What do you want to focus on?</h1>
        <div><br></div>
        <h3 class="mt-1 "> Click on one, two or three areas (zB) </h3>
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
              @click="goTo"
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

                    <div>
                      <p class="ma-0 text-body-1 font-weight-bold">
                        {{ item.text }}
                      </p>
                      <p class="text-caption font-weight-bold">
                        {{ item.subtext }}
                      </p>
                    </div>

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
  </v-container>
</template>

<script>

export default {
  name: 'TwoPage',
  methods: {
    goTo () {
      this.$router.push('/fourPage')
    }
  },
  data: () => ({
    icons: ['mdi-weight-lifter'],
    items: [
      {
        title: 'Legs',
        img: '/legs.png'
      },
      {
        title: 'Arms',
        img: '/arms.jpg'
      },
      {
        title: 'Back',
        img: '/back.jpg'
      },
      {
        title: 'Chest',
        img: '/chest.jpg'
      },
      {
        title: 'Glutes',
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

.title {
}
</style>
