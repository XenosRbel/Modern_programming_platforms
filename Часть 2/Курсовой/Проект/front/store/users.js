export const state = () => ({
    data: []
})

export const mutations = {
    storeData: function (state, data) {
        state.data = data
    }
}