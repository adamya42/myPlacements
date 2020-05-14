import React from "react";
import { FusePageCarded } from "@fuse";
//import withReducer from "app/main/apps/e-commerce/addMultipleUsers/node_modules/app/store/withReducer";
// import ProductsTable fro../products/ProductsTableble";
// import ProductsHeader fro../products/ProductsHeaderder";
import reducer from "../store/reducers";
import MultiUserHeader from "./MultiUserHeader";
import MultiUserForm from "./MultiUserForm";
import withReducer from "app/store/withReducer";

function AddMultipleUsers() {
  return (
    <React.Fragment>
      <FusePageCarded
        classes={{
          content: "flex",
          header: "min-h-72 h-72 sm:h-136 sm:min-h-136",
        }}
        header={<MultiUserHeader />}
        content={<MultiUserForm />}
        innerScroll
      />
    </React.Fragment>
  );
}

export default withReducer("eCommerceApp", reducer)(AddMultipleUsers);
