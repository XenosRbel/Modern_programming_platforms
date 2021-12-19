export default {
    buildModules: [
        "@nuxtjs/vuetify"
    ],
    modules: [
        "@nuxtjs/axios",
    ],
    plugins: [
        '~/plugins/axios'
    ],
    axios: {
        baseURL: 'http://localhost:8080/',
    },
    components: true,
    mode: 'universal',
}