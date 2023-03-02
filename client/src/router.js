import {createWebHistory, createRouter} from "vue-router";
import Join from "@/components/Join.vue";

const routes = [
    {
        path: "/",
    },
    {
        path: "/join",
        component: Join
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;