<template>
  <v-row class="height-100">
    <v-col cols="3">
      <Projects/>
    </v-col>
    <v-col cols="9">
      <ProjectForm :projectFormOpen="projectFormOpen" @update="projectFormOpen = $event"/>
      <v-card v-if="projects.length===0" elevation="2" align="center" justify="center" height="100%"
              class="pa-2 d-flex flex-column justify-center">
        <v-card-text>
          There are no projects yet.
        </v-card-text>
        <v-card-text>
          <v-btn color="primary" @click="projectFormOpen = true">
            CREATE
          </v-btn>
        </v-card-text>
        <v-card-text>
          the first one!
        </v-card-text>
      </v-card>
      <Project v-if="$store.state.projects.selectedProject"/>
    </v-col>
  </v-row>
</template>

<script>
import auth from '../utils/auth'

export default {
  name: "dashboard",
  middleware: 'authenticated',
  data() {
    return {
      projectFormOpen: false
    }
  },
  computed: {
    projects() {
      return this.$store.state.projects.data
    }
  },
  methods: {},
  created() {
    if (!this.$store.state.user.data) {
      this.$store.commit("user/storeData", auth.getUser())
    }
  }
}
</script>

<style scoped>
.height-100 {
  height: 100%;
}
</style>