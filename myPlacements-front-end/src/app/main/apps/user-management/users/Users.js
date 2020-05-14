import React from "react";
import { FusePageCarded } from "@fuse";
import withReducer from "app/store/withReducer";
import UsersTable from "./UsersTable";
import UsersHeader from "./UsersHeader";
import reducer from "../store/reducers";
import AddUserDialog from "./AddUserDialog";

function Users() {
  return (
    <React.Fragment>
      <FusePageCarded
        classes={{
          content: "flex",
          header: "min-h-72 h-72 sm:h-136 sm:min-h-136",
        }}
        header={<UsersHeader />}
        content={<UsersTable />}
        innerScroll
      />
      <AddUserDialog />
    </React.Fragment>
  );
}

export default withReducer("userManagerApp", reducer)(Users);
