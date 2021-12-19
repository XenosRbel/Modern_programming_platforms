<template>
  <v-form>
    <h2>Register</h2>
    <v-text-field v-model="username" label="Username"></v-text-field>
    <v-text-field v-model="password" label="Password" type="password"></v-text-field>
    <v-text-field v-model="email" label="Email"></v-text-field>
    <v-text-field v-model="name" label="Name"></v-text-field>
    <v-btn @click="register({username, password, name, email})">Register</v-btn>
  </v-form>
</template>

<script>
export default {
  name: "Register",
  data() {
    return {
      username: '',
      password: '',
      email: '',
      name: '',
    }
  },
  methods: {
    async register(credentials) {
      await this.$axios.post("users/register", credentials)
      await this.$router.push('/login')
    }
  }
}
</script>

<style scoped>

</style>