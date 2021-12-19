export const state = () => ({
    data: [],
    selectedProject: null
})

export const mutations = {
    storeData: function (state, data) {
        state.data = data
    },
    setSelectedProject: function (state, data) {
        state.selectedProject = data
        if (state.selectedProject) {
            state.selectedProject.tasks = data.tasks.sort((a, b) => {
                if (a.status === 'Closed' || b.status === 'Closed') return -1
                const kA = new Date(a.createdAt)
                const kB = new Date(b.createdAt)
                if (kA < kB) return 1;
                if (kA > kB) return -1;
                return 0;
            })
        }
    }
}