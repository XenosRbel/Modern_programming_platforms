<template>
  <v-row>
    <v-col>
      <Form/>
    </v-col>
    <v-col>
      <Table/>
    </v-col>
  </v-row>
</template>

<script>
export default {
  layout: "forms",
  name: "index",
  middleware: 'authenticated',
}
</script>

<style scoped>

</style>