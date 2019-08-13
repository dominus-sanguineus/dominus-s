import Vue from 'vue'
import VueResource from 'vue-resource'
import VueRouter from 'vue-router'
import VueHightlightJS from 'vue-highlightjs'
import Vuex from 'vuex'
import 'bootstrap'

import styles from './style/main.sass'////

Vue.use(VueResource)
Vue.use(VueRouter)
Vue.use(VueHightlightJS)
Vue.use(Vuex)
import App from '../components/App'

const store = new Vuex.Store({
    state: {

    },
    mutations: {

    },
    getters: {

    },

})

new Vue({
    el: "#app",
    template: '<app/>',
    store,
    components: {
        App
    }
})
