export const state = () => ({
    id: 0,
    name: '',
    username: '',
    email: '',
    password: '',
    roles: [],
    projects: [],
    data: null
})

export const mutations = {
    storeId: (state, data) => {
        state.id = data
    },
    storeName: (state, data) => {
        state.name = data
    },
    storeUsername: (state, data) => {
        state.username = data
    },
    storeEmail: (state, data) => {
        state.email = data
    },
    storePassword: (state, data) => {
        state.password = data
    },
    storeData: (state, data) => {
        state.name = data.name
        state.id = data.id
        state.username = data.username
        state.email = data.email
        state.roles = data.roles
        state.projects = data.projects
    }
}