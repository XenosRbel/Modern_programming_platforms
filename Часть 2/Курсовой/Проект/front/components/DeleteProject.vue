<template>
  <v-row justify="center">
    <v-dialog
        v-model="dialog"
        persistent
        max-width="490"
    >
      <v-card>
        <v-card-title class="text-h5">
          Do you really want to delete this project?
        </v-card-title>
        <v-card-text>If you delete this project you'll can't restore it anymore</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              color="green darken-1"
              text
              @click="dialog = false"
          >
            Cancel
          </v-btn>
          <v-btn
              color="green darken-1"
              text
              @click="removeProject"
          >
            Yes, delete it
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
export default {
  name: "DeleteProject",
  props: ['deleteProjectOpen', 'projectToDelete'],
  computed: {
    dialog: {
      get() {
        return this.deleteProjectOpen
      },
      set(newVal) {
        this.$emit('update', newVal)
      }
    },
  },
  methods: {
    async removeProject() {
      if(this.projectToDelete) {
        await this.$axios.delete("projects/" + this.projectToDelete.id);
        this.$store.commit(
            "projects/storeData",
            (await this.$axios.get("projects/")).data
        )
        this.dialog = false
      }
    }
  }
}
</script>

<style scoped>

</style>