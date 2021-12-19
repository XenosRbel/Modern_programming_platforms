<template>
  <v-card
      class="mx-auto height-100"
      max-width="500"
      max-height="100vh"
  >
    <v-toolbar
        color="primary"
        dark
    >
      <v-toolbar-title v-if="!searchTask">TASKS</v-toolbar-title>

      <v-spacer></v-spacer>
      <v-text-field solo light hide-details v-model="search" label="Task title..."
                    v-if="searchTask"></v-text-field>
      <v-btn icon @click="() => {searchTask = !searchTask; search = null}">
        <v-icon>mdi-magnify</v-icon>
      </v-btn>
      <v-btn icon @click="taskFormOpen = true">
        <v-icon>mdi-plus</v-icon>
      </v-btn>
    </v-toolbar>

    <v-list two-line style="max-height: 564px;" v-if="tasks && tasks.length">
      <v-list-item-group
          v-model="selected"
          active-class="pink--text"
      >
        <template v-for="(task, index) in tasks">
          <v-list-item :key="task.title + index">
            <template v-slot:default="{ active }">
              <v-list-item-content>
                <v-list-item-title v-text="task.title"></v-list-item-title>
                <v-list-item-subtitle v-text="task.description"></v-list-item-subtitle>
              </v-list-item-content>

              <v-list-item-action>
                <v-list-item-action-text v-text="getDate(task.createdAt)"></v-list-item-action-text>
                <v-list-item-action-text>
                  <v-icon
                      v-if="task.status==='Closed'"
                      color="green"
                      right
                  >
                    mdi-checkbox-marked-circle
                  </v-icon>
                  <v-icon
                      v-if="task.priority==='Urgent'"
                      color="red"
                      right
                  >
                    mdi-alert-circle-outline
                  </v-icon>
                </v-list-item-action-text>
              </v-list-item-action>
            </template>
          </v-list-item>

          <v-divider
              v-if="index < tasks.length - 1"
              :key="index"
          ></v-divider>
        </template>
      </v-list-item-group>
    </v-list>
    <TaskForm :task-form-open="taskFormOpen" @close="taskFormOpen = $event" :clearForm="true"/>
  </v-card>
</template>

<script>
export default {
  name: "TaskList",
  props: ['project'],
  watch: {
    'project.id'() {
      this.selected = null
    },
    selected(idx) {
      if (this.tasks[idx]) {
        this.$store.commit(
            "task/storeData", this.tasks[idx]
        )
      }
    },
    tasks(val) {
      if (val.length) {
        this.selected = 0
      }
    }
  },
  data() {
    return {
      taskFormOpen: false,
      selected: null,
      searchTask: false,
      search: null
    }
  },
  methods: {
    getDate(date) {
      date = new Date(date).getTime()
      var seconds = Math.floor((new Date() - date) / 1000);

      var interval = seconds / 31536000;

      if (interval > 1) {
        return Math.floor(interval) + " years";
      }
      interval = seconds / 2592000;
      if (interval > 1) {
        return Math.floor(interval) + " months";
      }
      interval = seconds / 86400;
      if (interval > 1) {
        return Math.floor(interval) + " d";
      }
      interval = seconds / 3600;
      if (interval > 1) {
        return Math.floor(interval) + " h";
      }
      interval = seconds / 60;
      if (interval > 1) {
        return Math.floor(interval) + " m";
      }
      return Math.floor(seconds) + " s";
    }
  },
  computed: {
    tasks() {
      if (this.search) {
        let tasks = [...this.$store.state.projects.selectedProject.tasks]
        return tasks.filter((p) => p.title.includes(this.search))
      }
      return this.$store.state.projects.selectedProject.tasks
    },
    selectedProject() {
      return this.$store.state.projects.selectedProject
    }
  }
}
</script>

<style scoped>
.v-list {
  max-height: 564px;
  overflow-y: auto;
}
</style>