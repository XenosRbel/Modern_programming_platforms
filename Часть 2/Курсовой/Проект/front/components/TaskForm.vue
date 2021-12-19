<template>
  <div justify="center">
    <v-dialog
        v-model="dialog"
        persistent
        max-width="600px"
    >
      <v-card>
        <v-card-title>
          <span class="text-h5">{{ taskToEdit ? 'Update the task' : 'Add New Task' }}</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col
                  cols="12"
              >
                <v-text-field
                    label="Name of the task *"
                    required
                    v-model="title"
                ></v-text-field>
              </v-col>
              <v-col
                  cols="12"
              >
                <v-textarea
                    solo
                    name="input-7-4"
                    label="Description of the task *"
                    v-model="description"
                ></v-textarea>
              </v-col>
              <v-col cols="12">
                <label for="taskPriority">Priority</label>
                <v-select
                    id="taskPriority"
                    v-model="priority"
                    :items="priorities"
                    label="Task priority"
                    solo
                ></v-select>
              </v-col>
              <v-col cols="12">
                <v-text-field
                    label="Task Estimation *"
                    suffix="h"
                    type="number"
                    v-model="estimation"
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-autocomplete
                    v-model="selectedUser"
                    :loading="userLoading"
                    :items="users"
                    :search-input.sync="searchUser"
                    cache-items
                    flat
                    hide-no-data
                    hide-detailsd
                    label="Select performer"
                    item-value="id"
                    item-text="name"
                    solo-inverted
                ></v-autocomplete>
              </v-col>
            </v-row>
          </v-container>
          <small>*indicates required field</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              color="blue darken-1"
              text
              @click="dialog = false"
          >
            Close
          </v-btn>
          <v-btn
              color="blue darken-1"
              text
              @click="taskToEdit ? updateTask() : addNewTask()"
          >
            {{ taskToEdit ? 'Save' : 'Create' }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
export default {
  name: "TaskForm",
  props: ['taskFormOpen', 'taskToEdit', 'clearForm'],
  watch: {
    taskToEdit(val) {
      this.title = val.title
      this.description = val.description
      this.status = val.status
      this.priority = val.priority
      this.estimation = val.estimation
      this.selectedUser = val.user.id
    },
    clearForm() {
      this.title = null
      this.description = null
      this.status = 'NotStarted'
      this.priority = 'Medium'
      this.estimation = null
      this.selectedUser = null
    }
  },
  data() {
    return {
      priorities: [
        'Low', // gray
        'Medium', // blue
        'High', // orange
        'Urgent' // red
      ],
      title: '',
      description: '',
      estimation: 0.0,
      priority: 'Medium',
      searchUser: null,
      userLoading: false,
      selectedUser: null
    }
  },
  computed: {
    dialog: {
      get() {
        return this.taskFormOpen
      },
      set(newVal) {
        this.$emit('close', newVal)
      }
    },
    users() {
      return this.$store.state.users.data
    },
    task() {
      const task = {
        title: this.title,
        description: this.description,
        user_id: this.selectedUser,
        priority: this.priority,
        project_id: this.$store.state.projects.selectedProject.id,
        estimation: this.estimation
      }
      if (this.taskToEdit) {
        return {...this.taskToEdit, ...task}
      }
      return task
    }
  },
  async fetch() {
    this.$store.commit(
        "users/storeData",
        (await this.$axios.get("users/")).data
    )
  },
  methods: {
    updateTask() {
      let task = Object.assign({}, this.task)
      this.$store.commit(
          'task/storeData', task)
      this.dialog = false
      this.$emit('update', this.dialog)
    },
    async addNewTask() {
      const project = (await this.$axios.put("projects/add_task/" + this.$store.state.projects.selectedProject.id + "/" + this.selectedUser, this.task)).data
      this.dialog = false
      this.$store.commit(
          "projects/storeData",
          (await this.$axios.get("projects/")).data
      )
      this.$store.commit(
          "projects/setSelectedProject",
          project
      )
      this.$store.commit(
          'task/storeData',
          this.$store.state.projects.selectedProject.tasks.reduce((prev, current) => (prev.id > current.id) ? prev : current)
      )
    }
  },
  created() {
    if(this.taskToEdit) {
      this.title = this.taskToEdit.title
      this.description = this.taskToEdit.description
      this.status = this.taskToEdit.status
      this.priority = this.taskToEdit.priority
      this.estimation = this.taskToEdit.estimation
      this.selectedUser = this.taskToEdit.user.id
    }
  }
}
</script>

<style scoped>

</style>