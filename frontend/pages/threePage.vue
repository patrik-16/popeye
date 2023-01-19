<template>
  <v-container class="pa-4 text-center">
    <div class="my-6" />
    <div>
      <v-app-bar-title>
        <h1> What is your goal?
        </h1>
      </v-app-bar-title>
    </div>
    <div class="my-6" />
    <v-row
      class="fill-height"
      align="center"
      justify="center"
    >
      <template v-for="(item, i) in items">
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
                <v-card-title class="text-h6 white--text">
                  <v-row
                    class="fill-height flex-column"
                    justify="space-between"
                  >
                    <p class="mt-4 subheading text-left">
                      {{ item.title }}
                    </p>

                    <div>
                      <p class="ma-0 text-body-1 font-weight-bold font-italic text-left">
                        {{ item.text }}
                      </p>
                      <p class="text-caption font-weight-medium font-italic text-left">
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
  name: 'ThreePage',
  methods: {
    goTo () {
      this.$router.push('/threePlusPage')
    }
  },
  data: () => ({
    icons: ['mdi-weight-lifter'],
    items: [
      {
        title: 'Hypertrophy',
        text: 'Build muscles',
        subtext: '',
        img: 'hypertrophy.jpg'
      },
      {
        title: 'Strength',
        text: 'Get sronger',
        subtext: '',
        img: 'strength.webp'
      },
      {
        title: 'Conditioning',
        text: 'Build your condition',
        subtext: '',
        img: 'cardio.webp'
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
</style>
