<template>
  <div class="text-center">
    <v-dialog
        v-model="dialog"
        width="500"
    >
      <v-card>
        <v-card-title class="text-h5 grey lighten-2">
          Select the period for generating the report
        </v-card-title>

        <v-card-text align="center">
          <v-date-picker
              class="mt-2"
              v-model="dates"
              range
          ></v-date-picker>
          <v-row>
            <v-text-field
                v-model="dateRangeText"
                label="Date range"
                prepend-icon="mdi-calendar"
                readonly
            ></v-text-field>
          </v-row>
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              color="primary"
              text
              @click="download"
          >
            Download
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
export default {
  name: "SelectDatesPdfDialog",
  props: ['pdfDialogOpen'],
  data() {
    return {
      dates: ['2021-09-10', '2021-11-03'],
    }
  },
  computed: {
    dialog: {
      get() {
        return this.pdfDialogOpen
      },
      set(newVal) {
        this.$emit('update', newVal)
      },
    },
    dateRangeText () {
      return this.dates.join(' ~ ')
    },
  },
  methods: {
    download() {
      this.dialog = false
      this.$emit('download', {before: this.dates[0], after: this.dates[1]})
    }
  }
}
</script>

<style scoped>

</style>