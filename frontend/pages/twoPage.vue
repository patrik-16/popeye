<template>
  <div class="pa-lg-15">
    <v-container class="pa-lg-15 text-center">
      <v-row>
        <v-card-text
          class="text-sm-subtitle-1"
        >
          <h2>How much experience do you have in the gym?</h2>
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
                  height="15rem"
                >
                  <div class="pillar">
                    <v-card-title class="text-h4 white--text">
                      <v-row
                        class="fill-height flex-column"
                        justify="space-between"
                      >
                        <p class="mt-14 subheading">
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
                  </div>
                </v-img>
              </v-card>
            </v-hover>
          </v-col>
        </template>
      </v-row>
    </v-container>
  </div>
</template>

<script>

export default {
  name: 'TwoPage',
  methods: {
    submit (i) {
      if (i === 0) {
        this.$router.push('/fourPage')
        this.$data.experience = 'Beginner'
      } else if (i === 1) {
        this.$router.push('/threePage')
        this.$data.experience = 'Advanced'
      }
      localStorage.setItem('experience', this.$data.experience)
    },
    toNextPage () {
      if (this.item.title === 'Beginner') {
        this.$router.push('/threePage')
      } else {
        this.$router.push('/fourPage')
      }
    },
    toThreePage () {
      this.$router.push('/threePage')
    }
  },
  data: () => ({
    experience: '',
    icons: ['mdi-weight-lifter'],
    items: [
      {
        title: 'Beginner',
        text: 'Less than one year of gym experience',
        subtext: 'Learn the basics',
        img: '/basic.jpg'
      },
      {
        title: 'Intermediate',
        text: 'More than one year of gym experience',
        subtext: 'Let\'s upgrade your workout plan!',
        img: '/advanced.jpg'
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

</style>
