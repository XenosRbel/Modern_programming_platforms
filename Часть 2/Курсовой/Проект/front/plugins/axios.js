export default function ({$axios, redirect, store}) {
    $axios.onRequest(config => {
        if (store.state.auth.auth && store.state.auth.auth.accessToken) {
            config.headers.common['Authorization'] = `Bearer ${store.state.auth.auth.accessToken}`;
        } else {
            delete config.headers.common["Authorization"];
        }
    })

    $axios.onError(error => {
        const code = parseInt(error.response && error.response.status)
        if (code === 401 && error.response.data.path !== '/authenticate') {
            redirect('/login')
        }
        if (code === 500) {
            redirect('/login')
            return 'Please check your credentials'
        }
    })
}