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
      <v-spacer />
      <v-btn @click="toLanding">
        <v-toolbar-title class="v-toolbar-title">
          {{ title }}
        </v-toolbar-title>
        <img src="../assets/img/hantel.png"
             alt="Barbell icon"
             class="hantelImage"
        />
      </v-btn>
      <v-spacer />
      <v-btn @click="toLogin">
        Login
      </v-btn>
      <v-btn @click="toProfile">
        Profile
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
      <span>&copy; {{ new Date().getFullYear() }} Alvir Slavica, Lukenda Tina, Moser Christina, Mariel Patrik</span>
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
          to: '/formPage'
        },
        {
          icon: 'mdi-account-outline',
          title: 'Login',
          to: '/login'
        },
        /* TODO: only show if login is true ..... */
        {
          icon: 'mdi-account',
          title: 'Profile',
          to: '/profile'
        }
      ],
      title: 'Popeye'
    }
  },
  methods: {
    /**
     * Uses VueRouter to send the user to the formPage.vue
     */
    toLanding () {
      this.$router.push('/')
    },
    toProfile () {
      this.$router.push('/profile')
    },
    toLogin () {
      this.$router.push('/login')
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
</style>
