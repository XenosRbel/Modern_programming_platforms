<template>
  <v-row>
    <v-col>
      <Login/>
    </v-col>
  </v-row>
</template>

<script>
export default {
  layout: "forms",
  name: "login",
  middleware: 'notAuthenticated',
}
</script>

<style scoped>

</style>