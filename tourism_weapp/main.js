import App from './App'
import Vue from 'vue'
import cuCustom from './colorui/components/cu-custom.vue'
import './uni.promisify.adaptor'
import Empty from "./components/empty/empty.vue"


Vue.config.productionTip = false
App.mpType = 'app'


Vue.component('Empty', Empty)
Vue.component('cu-custom',cuCustom)

const app = new Vue({
  ...App
})

app.$mount()
