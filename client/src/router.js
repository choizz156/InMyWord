import {createWebHistory, createRouter} from "vue-router";
import Join from "@/components/Join.vue";
import Login from "@/components/Login.vue";
import Home from "@/components/Home.vue";
import LoginHome from "@/components/LoginHome.vue";

const routes = [
    {
        path: "/",
        component: Home,
    },
    {
        path: "/auth",
        component: LoginHome,
    },
    {
        path: "/join",
        component: Join
    }, {
        path: "/login-form",
        component: Login
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;