<template>
  <div>
    <v-app>
      <v-main>
        <v-app-bar>
          <v-app-bar-title class="ml-6">
            Dashboard
          </v-app-bar-title>
          <v-spacer></v-spacer>

          <v-btn color="primary" v-if="$store.state.auth.auth" @click="logout">
            Logout
          </v-btn>
          <v-btn color="primary" v-else @click="$router.push('/login')">
            Login
          </v-btn>
          <v-btn class="ml-1" color="success" v-if="!$store.state.auth.auth" @click="$router.push('/register')">
            Register
          </v-btn>
        </v-app-bar>
        <v-container justify="center" align="center" fill-height fluid>
          <client-only>
            <Nuxt/>
          </client-only>
        </v-container>
      </v-main>
    </v-app>
  </div>
</template>

<script>
import auth from '../utils/auth'

export default {
  name: "default",
  methods: {
    logout() {
      auth.remove()
      this.$store.commit("auth/setAuth", null)
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>

</style>