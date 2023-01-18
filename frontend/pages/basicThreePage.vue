<template>
  <v-hover v-slot="{ hover }">
    <v-card
      class="mx-auto"
      color="grey lighten-4"
      max-width="600"
      @click="toLanding"
    >
      <v-img
        :aspect-ratio="16/9"
        src="basic.jpg"
        gradient="to top right, rgba(100,115,201,.33), rgba(25,32,72,.7)"
      >
        <v-expand-transition>
          <div
            v-if="hover"
            class="d-flex transition-fast-in-fast-out black darken-2 v-card--reveal text-h2 white--text"
            style="height: 100%;"
          >
            Learn the basics
          </div>
        </v-expand-transition>
      </v-img>
      <v-card-text
        class="pt-6"
        style="position: relative;"
      >
        <div class="font-weight-light grey--text text-h6 mb-2">
          Welcome to Popeye!
        </div>
        <h3 class="text-h4 font-weight-light blue-grey--text mb-2">
          Let's get startet!
        </h3>
        <div class="font-weight-light text-h6 mb-2">
          Learn the training fundamentals for your perfect workout.<br>
          Click to start
        </div>
      </v-card-text>
    </v-card>
  </v-hover>
</template>

<script>
export default {
  name: 'BasicThreePage'
}
</script>

<style>
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: .5;
  position: absolute;
  width: 100%;
}
</style>
