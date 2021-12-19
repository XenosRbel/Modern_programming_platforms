<template>
  <div class="text-center">
    <v-dialog
        v-model="dialog"
        width="500"
    >
      <v-card>
        <v-card-title class="text-h5 grey lighten-2">
          Log your work time
        </v-card-title>

        <v-card-text>
          <v-text-field
              label="Work time *"
              suffix="h"
              type="number"
              v-model="workTime"
          ></v-text-field>
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              color="primary"
              text
              @click="dialog = false"
          >
            Add work time
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
export default {
  name: "WorkLogDialog",
  props: ['workLogOpen'],
  data() {
    return {
      workTime: '0.0'
    }
  },
  computed: {
    dialog: {
      get() {
        return this.workLogOpen
      },
      set(newVal) {
        if (this.task) {
          const workTime = Number(this.task.workTime) + Number(this.workTime)
          const task = Object.assign({}, this.task)
          this.$store.commit(
              "task/storeData", Object.assign(task, {workTime: workTime})
          )
        }
        this.$emit('update', newVal)
      },
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
}
</script>

<style scoped>

</style>