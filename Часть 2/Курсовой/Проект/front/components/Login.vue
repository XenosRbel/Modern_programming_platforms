<template>
  <v-form>
    <h2>Login</h2>
    <v-text-field v-model="username" label="Username"></v-text-field>
    <v-text-field v-model="password" label="Password" type="password"></v-text-field>
    <v-btn @click="login({username, password})">Login</v-btn>
    <v-spacer></v-spacer>
    <v-alert
        v-if="loginError"
        dense
        outlined
        type="error"
    >
      {{ loginError }}
    </v-alert>
  </v-form>
</template>

<script>
import auth from '../utils/auth'

export default {
  name: "Login",
  data() {
    return {
      username: '',
      password: '',
      loginError: ''
    }
  },
  methods: {
    async login(credentials) {
      const resp = await this.$axios.post("authenticate", credentials)
      if (resp.status == 200) {
        const authObj = {
          accessToken: resp.data.token
        }
        this.$store.commit('auth/setAuth', authObj) // mutating to store for client rendering
        auth.set(authObj)

        const user = (await this.$axios.post("users/get_by_username", {
          username: this.username
        })).data
        auth.setUser(user)
        this.$store.commit("user/storeData", user)
        await this.$router.push('/dashboard')
        this.$store.commit(
            "users/storeData",
            (await this.$axios.get("users/")).data
        )
      } else {
        this.loginError = resp
      }
    }
  }
}
</script>

<style scoped>

</style>