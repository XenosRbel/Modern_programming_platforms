export const state = () => {
    return {
        auth: null
    }
}
export const mutations = {
    setAuth (state, auth) {
        state.auth = auth
    }
}
