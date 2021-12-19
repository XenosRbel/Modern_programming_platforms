<template>
  <v-card
      elevation="2"
      class="mx-auto height-100"
  >
    <v-container class="height-100">
      <v-row class="project-info" align="center">
<!--        <v-subheader>{{ project.name }}</v-subheader>-->
<!--        <v-spacer></v-spacer>-->
<!--        <v-subheader v-if="project.status==='Open'">Project was launched on {{ new Date(project.startAt).toDateString() }}</v-subheader>-->
<!--        <v-subheader v-if="project.status==='Closed'">Project was closed {{ new Date(project.endAt).toDateString() }}</v-subheader>-->
      </v-row>
      <v-row class="height-100">
        <v-col cols="4" style="padding: 0;" class="height-100">
          <TaskList :project="project"/>
        </v-col>
        <v-col cols="8" class="py-0 pr-0">
          <Task v-if="$store.state.task.data"/>
        </v-col>
      </v-row>
    </v-container>
  </v-card>
</template>

<script>
export default {
  name: "Project",
  computed: {
    project() {
      return this.$store.state.projects.selectedProject
    }
  }
}
</script>

<style scoped>

</style>