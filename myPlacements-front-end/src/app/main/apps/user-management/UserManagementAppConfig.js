import React from "react";
import { Redirect } from "react-router-dom";

export const UserManagementAppConfig = {
  settings: {
    layout: {},
  },
  routes: [
    {
      // path: "/apps/e-commerce/Users/:UserId/:UserHandle?",
      path: "/apps/user-manager/users/:userId",
      component: React.lazy(() => import("./user/User")),
    },
    {
      path: "/apps/user-manager/users",
      component: React.lazy(() => import("./users/Users")),
    },

    {
      path: "/apps/user-manager/addMultipleUsers",
      component: React.lazy(() =>
        import("./addMultipleUsers/AddMultipleUsers")
      ),
    },
    {
      path: "/apps/user-manager",
      component: () => <Redirect to="/apps/user-manager/users" />,
    },
  ],
};
