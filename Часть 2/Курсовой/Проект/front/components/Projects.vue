<template>
  <v-card
      height="100%"
      elevation="2"
      class="mx-auto"
      max-width="300"
  >
    <v-toolbar
        color="primary"
        dark
    >
      <v-toolbar-title v-if="!searchProject">PROJECTS</v-toolbar-title>

      <v-spacer></v-spacer>
      <v-text-field solo light hide-details v-model="search" label="Project name..."
                    v-if="searchProject"></v-text-field>
      <v-btn icon @click="() => {searchProject = !searchProject; search = null}">
        <v-icon>mdi-magnify</v-icon>
      </v-btn>
      <v-btn icon @click="projectFormOpen = true">
        <v-icon>mdi-plus</v-icon>
      </v-btn>
      <v-btn icon @click="pdfDialogOpen = true">
        <v-icon>mdi-cloud-download</v-icon>
      </v-btn>
    </v-toolbar>
    <v-list rounded>
      <v-list-item-group
          v-model="project"
          color="primary"
      >
        <v-list-item
            v-for="(item, i) in projects"
            :key="i+item.id"
        >
          <v-list-item-content>
            <v-list-item-title v-text="item.name"></v-list-item-title>
          </v-list-item-content>
          <v-list-item-action>
            <v-btn
                fab
                dark
                small
                color="error"
                @click="deleteProject(item)"
            >
              <v-icon dark>
                mdi-delete
              </v-icon>
            </v-btn>
          </v-list-item-action>
        </v-list-item>
      </v-list-item-group>
    </v-list>
    <DeleteProject :delete-project-open="deleteProjectOpen" :project-to-delete="projectToDelete"
                   @update="deleteProjectOpen = $event"/>
    <ProjectForm :projectFormOpen="projectFormOpen" @update="projectFormOpen = $event"/>
    <SelectDatesPdfDialog :pdfDialogOpen="pdfDialogOpen" @update="pdfDialogOpen = $event" @download="downloadPdf($event)"/>
  </v-card>
</template>

<script>
import { saveAs } from 'file-saver';
export default {
  name: "Projects",
  data: () => ({
    project: null,
    deleteProjectOpen: false,
    pdfDialogOpen: false,
    projectToDelete: null,
    projectFormOpen: false,
    searchProject: false,
    search: null
  }),
  watch: {
    selectedProjectIdx(val) {
      this.$store.commit(
          "projects/setSelectedProject", this.projects[val])
      this.project = val
      this.$store.commit(
          "task/storeData", null
      )
    },
    selectedProject(val) {
      if (val) {
        this.project = this.projects.findIndex((p) => p.id === val.id)
      }
    }
  },
  computed: {
    projects() {
      if (this.search) {
        let projects = [...this.$store.state.projects.data]
        return projects.filter((p) => p.name.includes(this.search))
      }
      return this.$store.state.projects.data
    },
    selectedProjectIdx() {
      let proj = null
      if (this.project !== null && this.projects[this.project]) {
        proj = this.project
      } else if (this.$store.state.projects.data.length) {
        proj = this.$store.state.projects.data.length - 1
      }
      return proj
    },
    selectedProject() {
      return this.$store.state.projects.selectedProject
    }
  },
  async fetch() {
    this.$store.commit(
        "projects/storeData",
        (await this.$axios.get("projects/")).data
    )
    if (this.projects.length) {
      this.project = this.projects.length - 1
    }
  },
  methods: {
    deleteProject(project) {
      this.deleteProjectOpen = true
      this.projectToDelete = project
    },
    async downloadPdf({before, after}) {
      const pdfString = (await this.$axios.get(`projects/export/pdf/${before}/${after}`, {
        responseType: 'arraybuffer'
      })).data
      // Create blob link to download
      let blob = new Blob([pdfString], {type: 'application/pdf'});

      saveAs(blob,'projects.pdf');
    }
  }
}
</script>

<style scoped>

</style>