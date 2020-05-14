import React from "react";
import { Redirect } from "react-router-dom";

export const ECommerceAppConfig = {
  settings: {
    layout: {},
  },
  routes: [
    {
      // path: "/apps/e-commerce/products/:productId/:productHandle?",
      path: "/apps/user-manager/users/:productId",
      component: React.lazy(() => import("./product/Product")),
    },
    {
      path: "/apps/user-manager/users",
      component: React.lazy(() => import("./products/Products")),
    },
    {
      path: "/apps/e-commerce/orders/:orderId",
      component: React.lazy(() => import("./order/Order")),
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
