import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: () => import("@/views/MyHomeView.vue"),
    },
    {
      path: "/review",
      name: "review",
      component: () => import("@/views/ReviewView.vue"),
      redirect: "/review/list/:contentId",
      children: [
        {
          path: "create/:contentId",
          name: "ReviewCreate",
          component: () => import("@/components/review/ReviewCreate.vue"),
          beforeEnter: () => {
            if (!sessionStorage.getItem("userInfo")) {
              alert("로그인이 필요한 서비스입니다.");
              return false;
            } else true;
          },
        },
        {
          path: "list/:contentId",
          name: "ReviewList",
          component: () => import("@/components/review/ReviewList.vue"),
        },
        {
          path: ":contentId/modify/:reviewId",
          name: "ReviewModify",
          component: () => import("@/components/review/ReviewModify.vue"),
          beforeEnter: () => {
            if (!sessionStorage.getItem("userInfo")) {
              alert("로그인이 필요한 서비스입니다.");
              return false;
            } else true;
          },
        },
        {
          path: ":contentId/:reviewId",
          name: "ReviewDetail",
          component: () => import("@/components/review/ReviewDetail.vue"),
          beforeEnter: () => {
            if (!sessionStorage.getItem("userInfo")) {
              alert("로그인이 필요한 서비스입니다.");
              return false;
            } else true;
          },
        },
      ],
    },

    {
      path: "/course",
      name: "course",
      component: () => import("@/views/CourseView.vue"),
      redirect: "/course/list",
      children: [
        {
          path: "create",
          name: "CourseCreate",
          component: () => import("@/components/course/CourseCreate.vue"),
          beforeEnter: () => {
            if (!sessionStorage.getItem("userInfo")) {
              alert("로그인이 필요한 서비스입니다.");
              return false;
            } else true;
          },
        },
        {
          path: "list",
          name: "CourseList",
          component: () => import("@/components/course/CourseList.vue"),
        },
        {
          path: "detail/:courseId",
          name: "CourseDetail",
          component: () => import("@/components/course/CourseDetail.vue"),
          beforeEnter: () => {
            if (!sessionStorage.getItem("userInfo")) {
              alert("로그인이 필요한 서비스입니다.");
              return false;
            } else true;
          },
        },
      ],
    },

    {
      path: "/trip",
      name: "trip",
      component: () => import("@/views/TripView.vue"),
    },
    {
      path: "/admin",
      name: "admin",
      component: () => import("@/views/AdminView.vue"),
    },
    {
      path: "/theme_main",
      name: "ThemeMenu",
      component: () => import("@/components/main/ThemeMenu.vue"),
    },
    {
      path: "/attraction/category/:category",
      name: "ThemeList",
      component: () => import("@/components/board/ThemeList.vue"),
    },
    {
      path: "/detail/:contentId",
      name: "AttractionDetail",
      component: () => import("@/components/attraction/AttractionDetail.vue"),
    },
    {
      path: "/FAQ",
      name: "FAQBoard",
      component: () => import("@/components/board/FAQBoard.vue"),
    },
  ],
});

export default router;
