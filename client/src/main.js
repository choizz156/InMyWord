import { createApp } from 'vue'
import App from './App.vue'
import router from "@/router";
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import VerEx from 'verbal-expressions';

createApp(App).use(router).use(VerEx).mount('#app')
