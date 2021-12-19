<template>
  <div justify="center">
    <v-dialog
        v-model="dialog"
        persistent
        max-width="600px"
    >
      <v-card>
        <v-card-title>
          <span class="text-h5">Add New Project</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col
                  cols="12"
              >
                <v-text-field
                    label="Name of the project*"
                    required
                    v-model="name"
                ></v-text-field>
              </v-col>
              <v-col
                  cols="12"
              >
                <v-textarea
                    solo
                    name="input-7-4"
                    label="Description of the project"
                    v-model="description"
                ></v-textarea>
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
              @click="addNewProject"
          >
            Create
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
export default {
  name: "ProjectForm",
  props: ['projectFormOpen'],
  data() {
    return {
      name: '',
      description: ''
    }
  },
  computed: {
    dialog: {
      get() {
        return this.projectFormOpen
      },
      set(newVal) {
        this.$emit('update', newVal)
      }
    },
    project() {
      return {
        name: this.name,
        description: this.description,
        user_id: this.$store.state.user.id
      }
    }
  },
  methods: {
    async addNewProject() {
      const project = await this.$axios.post("projects/create", this.project)
      this.$store.commit(
          "projects/storeData",
          (await this.$axios.get("projects/")).data
      )
      this.$store.commit(
          "projects/setSelectedProject",
          project.data
      )
      this.dialog = false
    }
  },
  async fetch() {
    this.$store.commit(
        "users/storeData",
        (await this.$axios.get("users/")).data
    )
  },
}
</script>

<style scoped>

</style>