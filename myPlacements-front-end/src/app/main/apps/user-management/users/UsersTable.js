import React, { useEffect, useState } from "react";
import { Icon, Checkbox, Typography } from "@material-ui/core";
import { FuseScrollbars } from "@fuse";
import { withRouter } from "react-router-dom";
// import clsx from "clsx";
// import _ from "@lodash";
// import ProductsTableHead from "./ProductsTableHead";
import * as Actions from "../store/actions";
import { useDispatch, useSelector } from "react-redux";
import ReactTable from "react-table";
import { FuseUtils, FuseAnimate } from "@fuse";
import UsersMultiSelectMenu from "./UsersMultiselectMenu";

function UsersTable(props) {
  const dispatch = useDispatch();
  const users = useSelector(({ userManagerApp }) => userManagerApp.users.data);
  const searchText = useSelector(
    ({ userManagerApp }) => userManagerApp.users.searchText
  );

  const selectedUserIds = useSelector(
    ({ userManagerApp }) => userManagerApp.users.selectedUserIds
  );

  //const [selected, setSelected] = useState([]);
  // const [data, setData] = useState(users);
  // const [page, setPage] = useState(0);
  // const [rowsPerPage, setRowsPerPage] = useState(10);
  // const [order, setOrder] = useState({
  //   direction: "asc",
  //   id: null,
  // });

  useEffect(() => {
    dispatch(Actions.getUsers());
  }, [dispatch]);

  function handleClick(item) {
    props.history.push("/apps/user-manager/users/" + item.id);
  }
  //+ "/" + item.handle

  //###################################   START   ###########################################

  const [filteredData, setFilteredData] = useState(null);

  useEffect(() => {
    function getFilteredArray(data, searchText) {
      const arr = Object.keys(data).map((id) => data[id]);
      if (searchText.length === 0) {
        return arr;
      }
      return FuseUtils.filterArrayByString(arr, searchText);
    }

    if (users) {
      setFilteredData(getFilteredArray(users, searchText));
    }
  }, [users, searchText]);

  if (!filteredData) {
    return null;
  }

  if (filteredData.length === 0) {
    return (
      <div className="flex flex-1 items-center justify-center h-full">
        <Typography color="textSecondary" variant="h5">
          There are no users!
        </Typography>
      </div>
    );
  }

  //######################################   END   #########################################

  return (
    <div className="w-full flex flex-col">
      <FuseAnimate animation="transition.slideUpIn" delay={300}>
        <FuseScrollbars className="flex-grow overflow-x-auto">
          <ReactTable
            className="min-w-xl"
            aria-labelledby="tableTitle"
            getTrProps={(state, rowInfo, column) => {
              return {
                className: "cursor-pointer",
                onClick: (e, handleOriginal) => {
                  if (rowInfo) {
                    handleClick(rowInfo.original);
                  }
                },
              };
            }}
            data={filteredData}
            columns={[
              {
                Header: () => (
                  <Checkbox
                    onClick={(event) => {
                      event.stopPropagation();
                    }}
                    onChange={(event) => {
                      event.target.checked
                        ? dispatch(Actions.selectAllUsers())
                        : dispatch(Actions.deSelectAllUsers());
                    }}
                    checked={
                      selectedUserIds.length === Object.keys(users).length &&
                      selectedUserIds.length > 0
                    }
                    indeterminate={
                      selectedUserIds.length !== Object.keys(users).length &&
                      selectedUserIds.length > 0
                    }
                  />
                ),
                accessor: "",
                Cell: (row) => {
                  return (
                    <Checkbox
                      onClick={(event) => {
                        event.stopPropagation();
                      }}
                      checked={selectedUserIds.includes(row.value.id)}
                      onChange={() =>
                        dispatch(Actions.toggleInSelectedUsers(row.value.id))
                      }
                    />
                  );
                },
                className: "justify-center",
                sortable: false,
                width: 64,
              },
              {
                Header: () =>
                  selectedUserIds.length > 0 && <UsersMultiSelectMenu />,
                accessor: "avatar",
                Cell: "",
                className: "justify-center",
                width: 64,
                sortable: false,
              },
              {
                Header: "Name",
                accessor: "name",
                filterable: true,
                className: "font-bold",
              },
              {
                Header: "UniqueID",
                accessor: "uniqueId",
                filterable: true,
                className: "font-bold",
              },
              {
                Header: "Role",
                accessor: "role",
                filterable: true,
                className: "font-bold",
              },
              {
                Header: "Email",
                accessor: "email",
                filterable: true,
                className: "font-bold",
              },
              {
                Header: "Created On",
                accessor: "createdOn",
                filterable: true,
                className: "font-bold",
              },
              {
                Header: "Active",
                accessor: "active",
                width: 64,
                Cell: (row) => {
                  return row.original.active ? (
                    <Icon className="text-green text-20">check_circle</Icon>
                  ) : (
                    <Icon className="text-red text-20">remove_circle</Icon>
                  );
                },
                filterable: false,
                sortable: true,
                className: "font-bold",
              },
              {
                Header: "Verified",
                accessor: "verified",
                width: 64,
                Cell: (row) => {
                  return row.original.verified ? (
                    <Icon className="text-green text-20">check_circle</Icon>
                  ) : (
                    <Icon className="text-red text-20">remove_circle</Icon>
                  );
                },
                filterable: false,
                sortable: true,
                className: "font-bold",
              },
            ]}
            defaultPageSize={10}
            noDataText="No users found"
          />
        </FuseScrollbars>
      </FuseAnimate>
    </div>
  );
}

export default withRouter(UsersTable);
