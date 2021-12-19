<template>
  <v-form>
    <h2>Form</h2>
    <v-text-field v-model="name" label="Name"></v-text-field>
    <v-text-field v-model="username" label="Username"></v-text-field>
    <v-text-field v-model="email" label="Email"></v-text-field>
    <v-btn @click="submit({id, name, password, email, username})">{{ id ? 'Edit' : 'Submit' }}</v-btn>
  </v-form>
</template>

<script>
export default {
  name: "Form",
  data() {
    return {
    }
  },
  computed: {
    id: {
      get() {
        return this.$store.state.user.id
      },
      set(v) {
        this.$store.commit("user/storeId", v)
      }
    },
    name: {
      get() {
        return this.$store.state.user.name
      },
      set(v) {
        this.$store.commit("user/storeName", v)
      }
    },
    username: {
      get() {
        return this.$store.state.user.username
      },
      set(v) {
        this.$store.commit("user/storeUsername", v)
      }
    },
    password: {
      get() {
        return this.$store.state.user.password
      },
      set(v) {
        this.$store.commit("user/storePassword", v)
      }
    },
    email: {
      get() {
        return this.$store.state.user.email
      },
      set(v) {
        this.$store.commit("user/storeEmail", v)
      }
    },
  },
  methods: {
    async submit(user) {
      if (user.id) {
        await this.$axios.put("users/" + user.id, user)
      } else {
        await this.$axios.post("users/register", user)
      }
      await this.resetForm({id: 0, name: "", password: "", email: ""})
      this.$store.commit(
          "users/storeData",
          (await this.$axios.get("users/")).data
      )
    },
    async resetForm(user) {
      this.$store.commit("user/storeId", user.id)
      this.$store.commit("user/storeName", user.name)
      this.$store.commit("user/storePassword", user.password)
      this.$store.commit("user/storeEmail", user.email)
    }
  }
}
</script>

<style scoped>

</style>