<template>
  <v-data-table
      :headers="headers"
      :items="users"
      class="elevation-1"
  >
    <template v-slot:[`item.edit`]="{ item }">
      <v-btn color="success" @click="editItem(item)">Edit</v-btn>
    </template>
    <template v-slot:[`item.delete`]="{ item }">
      <v-btn color="warning" @click="deleteItem(item.id)">Delete</v-btn>
    </template>
  </v-data-table>
</template>

<script>
export default {
  name: "Table",
  data() {
    return {
      headers: [
        {
          text: 'Username',
          align: 'start',
          sortable: false,
          value: 'username',
        },
        {
          text: 'Name',
          value: 'name',
        },
        {text: 'Email', value: 'email'},
        {text: 'Edit', value: 'edit'},
        {text: 'Delete', value: 'delete'},
      ],
    }
  },
  computed: {
    users() {
      return this.$store.state.users.data
    }
  },
  async fetch() {
    this.$store.commit(
        "users/storeData",
        (await this.$axios.get("users/")).data
    )
  },
  methods: {
    async deleteItem(id) {
      await this.$axios.delete("users/" + id)
      this.$store.commit(
          "users/storeData",
          (await this.$axios.get("users/")).data
      )
    },
    async editItem(user) {
      this.$store.commit("user/storeId", user.id)
      this.$store.commit("user/storeName", user.name)
      this.$store.commit("user/storeUsername", user.username)
      this.$store.commit("user/storePassword", user.password)
      this.$store.commit("user/storeEmail", user.email)
    },
  }
}
</script>

<style scoped>

</style>