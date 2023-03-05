import { createApp } from 'vue'
import App from './App.vue'
import router from "@/router";
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import VerEx from 'verbal-expressions';
import store from "@/store";

createApp(App).use(router).use(store).use(VerEx).mount('#app')
