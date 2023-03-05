import { createStore } from 'vuex'

const store = createStore({
    state(){
        return {
            email : '',
        }
    },
    mutations: {
        setEmail(state, payload) {
            state.email = payload;
        }
    }
})

export default store