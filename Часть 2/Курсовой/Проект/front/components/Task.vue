<template>
  <div>
    <v-stepper alt-labels outlined tile>
      <v-stepper-header>
        <v-stepper-step
            step="1"
            :complete="task.status==='InProgress'"
        >
          Not Started
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step
            step="2"
            :complete="task.status==='InProgress' || task.status==='Submitted' || task.status==='Closed'"
        >
          In Process
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step
            step="3"
            :complete="task.status==='Submitted' || task.status==='Closed'"
        >
          Submitted
        </v-stepper-step>
        <v-divider></v-divider>

        <v-stepper-step
            step="4"
            :complete="task.status==='Closed'"
            color="green"
        >
          Closed
        </v-stepper-step>
      </v-stepper-header>
    </v-stepper>
    <v-card outlined
            tile>
      <v-row>
        <WorkLogDialog :workLogOpen="workLogOpen" @update="updateTask($event)"/>
        <TaskForm :taskFormOpen="taskFormOpen" :taskToEdit="task" @update="updateTask($event)"
                  @close="taskFormOpen=$event"/>
        <v-col cols="8" class="pr-0">
          <v-card>
            <v-card-text>
              <v-flex>
                <v-row>
                  <v-col>
                    <v-flex>
                      <v-row>
                        <v-col>{{ task.updatedAt ? getDate(task.updatedAt) : getDate(task.createdAt) }}</v-col>
                      </v-row>
                    </v-flex>
                  </v-col>
                  <v-col align="right">
                    <v-btn
                        class="mx-2"
                        height="30px"
                        width="30px"
                        fab
                        dark
                        small
                        color="primary"
                        @click="taskFormOpen = true"
                    >
                      <v-icon>
                        mdi-pencil
                      </v-icon>
                    </v-btn>

                  </v-col>
                </v-row>
              </v-flex>
              <p class="text-h4 text--primary">
                {{ task.title }}
              </p>
              <span v-if="task.user"><v-icon size="20">mdi-account</v-icon> Performer: {{ task.user.name }} </span>
              <p :style="{color:priorityColor}">{{ task.priority }} priority task</p>
              <div class="text--primary">
                {{ task.description }}
              </div>
              <v-timeline
                  dense
                  clipped
              >
                <v-timeline-item
                    fill-dot
                    class="white--text mb-12"
                    color="orange"
                >
                  <template v-slot:icon>
                    <v-icon size="20">mdi-pencil</v-icon>
                  </template>
                  <v-text-field
                      v-model="note"
                      hide-details
                      flat
                      label="Leave a note..."
                      solo
                  >
                    <template v-slot:append>
                      <v-btn
                          class="mx-0"
                          depressed
                          @click="comment"
                      >
                        Post
                      </v-btn>
                    </template>
                  </v-text-field>
                </v-timeline-item>

                <v-slide-x-transition
                    group
                >
                  <v-timeline-item
                      v-for="note in notes"
                      :key="note.id"
                      class="mb-4"
                      color="pink"
                      small
                  >
                    <v-row justify="space-between">
                      <v-col
                          cols="7"
                          v-text="note.text"
                      ></v-col>
                      <v-col
                          class="text-right"
                          cols="5"
                          v-text="getTimeAgo(note.createdAt)"
                      ></v-col>
                    </v-row>
                  </v-timeline-item>
                </v-slide-x-transition>
              </v-timeline>
            </v-card-text>
          </v-card>
        </v-col>
        <v-col cols="4" class="pl-1">
          <v-card>
            <v-card-text>
              <div class="font-weight-bold mb-2">
                Work Log
              </div>
              <v-flex>
                <v-row>
                  <v-col>
                    <v-btn
                        color="primary"
                        fab
                        dark
                        small
                        @click="workLogOpen = true"
                        height="35px"
                        width="35px"
                    >
                      <v-icon>mdi-calendar-clock</v-icon>
                    </v-btn>
                  </v-col>
                  <v-col>
                    {{ task.workTime || '0' }} / <span :style="{color: task.workTime > task.estimation ? 'red' : 'inherit'}">{{
                        task.estimation
                      }}</span>   h
                  </v-col>
                </v-row>
              </v-flex>

            </v-card-text>
          </v-card>
          <v-card class="mt-1">
            <v-card-text class="pb-0 mb-0">
              <div class="font-weight-bold mb-2">
                Task status
              </div>
              <v-select
                  id="taskPriority"
                  v-model="status"
                  :items="statuses"
                  item-text="text"
                  item-value="id"
                  label="Task priority"
                  solo
              ></v-select>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "Task",
  data() {
    return {
      workLogOpen: false,
      taskFormOpen: false,
      note: null
    }
  },
  computed: {
    priorityColor() {
      if (this.task.priority === 'Low') return 'grey'
      if (this.task.priority === 'Medium') return 'blue'
      if (this.task.priority === 'High') return 'orange'
      if (this.task.priority === 'Urgent') return 'red'
    },
    notes: {
      get() {
        if(this.task && this.task.notes) {
          return [...this.task.notes].sort((a, b) => {
            const kA = new Date(a.createdAt)
            const kB = new Date(b.createdAt)
            if (kA < kB) return 1;
            if (kA > kB) return -1;
            return 0;
          })
        }
        return []
      },
      set(val) {
        //
      }
    },
    statuses() {
      return [
        {
          id: 'NotStarted',
          text: 'Not Started'
        }, {
          id: 'InProgress',
          text: 'In Process'
        }, {
          id: 'Submitted',
          text: 'Submitted'
        }, {
          id: 'Closed',
          text: 'Closed'
        }
      ]
    },
    status: {
      get() {
        return this.task.status
      },
      async set(val) {
        let task = Object.assign({}, this.task)
        task.status = val
        task = (await this.$axios.put("tasks/" + this.task.id, task)).data
        this.$store.commit(
            "task/storeData", task
        )
      }
    },
    task: {
      get() {
        return this.$store.state.task.data
      },
      set(val) {
        this.$store.commit(
            "task/storeData", val
        )
      }
    }
  },
  methods: {
    async comment() {
      if (this.note) {
        let task = Object.assign({}, this.task)
        task.noteText = this.note
        this.task = (await this.$axios.put("tasks/" + task.id, task)).data
        this.note = null
      }
    },
    async updateTask(dialog) {
      this.task = (await this.$axios.put("tasks/" + this.task.id, this.task)).data
      this.workLogOpen = false
      this.taskFormOpen = false
    },
    getDate(date) {
      var time = new Date(date).getTime()
      var d = new Date()
      d.setTime(time)
      return d.toLocaleDateString()
    },
    getTimeAgo(date) {
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
}
</script>

<style scoped>

</style>