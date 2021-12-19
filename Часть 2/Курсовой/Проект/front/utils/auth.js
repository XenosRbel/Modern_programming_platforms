const Cookie = process.client ? require('js-cookie') : undefined
export default {
    get() {
        return JSON.parse(Cookie.get('auth'))
    },
    set(auth) {
        Cookie.set('auth', JSON.stringify(auth))
    },
    remove() {
        Cookie.remove('auth')
    },
    setUser(user) {
        Cookie.set('user', JSON.stringify(user))
    },
    getUser() {
        return JSON.parse(Cookie.get('user'))
    }
}