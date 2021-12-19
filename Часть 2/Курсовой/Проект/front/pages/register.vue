<template>
  <v-row>
    <v-col>
      <Register/>
    </v-col>
  </v-row>
</template>

<script>
export default {
  layout: "forms",
  name: "register",
  middleware: 'notAuthenticated',
}
</script>

<style scoped>

</style>