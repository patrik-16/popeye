<template>
  <v-app dark>
    <v-navigation-drawer
      v-model="drawer"
      :clipped="clipped"
      temporary
      app
    >
      <v-list>
        <v-list-item
          v-for="(item, i) in items"
          :key="i"
          :to="item.to"
          router
          exact
        >
          <v-list-item-action>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
    <v-app-bar
      :clipped-left="clipped"
      fixed
      app
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer" />
      <v-btn id="no-background-hover" depressed @click="toLanding">
        <v-toolbar-title class="v-toolbar-title">
          {{ title }}
        </v-toolbar-title>
        <img
          src="../assets/img/hantel.png"
          alt="Barbell icon"
          class="hantelImage"
        >
      </v-btn>
      <v-spacer />
      <v-btn id="no-background-hover" depressed @click="toForm">
        Start your Journey
      </v-btn>
    </v-app-bar>
    <v-main>
      <v-container>
        <Nuxt />
      </v-container>
    </v-main>
    <v-footer
      :absolute="!fixed"
      app
    >
      <span>&copy; {{ new Date().getFullYear() }} Popeye</span>
    </v-footer>
  </v-app>
</template>

<script>
export default {
  name: 'DefaultLayout',
  data () {
    return {
      clipped: false,
      drawer: false,
      fixed: false,
      items: [
        {
          icon: 'mdi-home-outline',
          title: 'Home',
          to: '/'
        },
        {
          icon: 'mdi-arrow-right',
          title: 'Start your Journey',
          to: '/onePage'
        },
        {
          icon: 'mdi-weight-lifter',
          title: 'view your Program',
          to: '/program'
        }
      ],
      title: 'Popeye'
    }
  },
  methods: {
    /**
     * Uses VueRouter to send the user to the termsAndConditions.vue
     */
    toLanding () {
      this.$router.push('/')
    },
    toProfile () {
      this.$router.push('/profile')
    },
    toLogin () {
      this.$router.push('/login')
    },
    toForm () {
      this.$router.push('/onePage')
    }
  }
}
</script>

<style scoped>
.v-toolbar-title {
  font-size: 2rem !important;
  font-weight: lighter;
}

.hantelImage {
  height: 2rem;
}

#no-background-hover::before {
  background-color: transparent !important
}

</style>
