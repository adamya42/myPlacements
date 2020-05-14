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
import ProductsMultiSelectMenu from "./ProductsMultiselectMenu";

function ProductsTable(props) {
  const dispatch = useDispatch();
  const products = useSelector(
    ({ eCommerceApp }) => eCommerceApp.products.data
  );
  const searchText = useSelector(
    ({ eCommerceApp }) => eCommerceApp.products.searchText
  );

  const selectedProductIds = useSelector(
    ({ eCommerceApp }) => eCommerceApp.products.selectedProductIds
  );

  //const [selected, setSelected] = useState([]);
  // const [data, setData] = useState(products);
  // const [page, setPage] = useState(0);
  // const [rowsPerPage, setRowsPerPage] = useState(10);
  // const [order, setOrder] = useState({
  //   direction: "asc",
  //   id: null,
  // });

  useEffect(() => {
    dispatch(Actions.getProducts());
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

    if (products) {
      setFilteredData(getFilteredArray(products, searchText));
    }
  }, [products, searchText]);

  if (!filteredData) {
    return null;
  }

  if (filteredData.length === 0) {
    return (
      <div className="flex flex-1 items-center justify-center h-full">
        <Typography color="textSecondary" variant="h5">
          There are no products!
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
                        ? dispatch(Actions.selectAllProducts())
                        : dispatch(Actions.deSelectAllProducts());
                    }}
                    checked={
                      selectedProductIds.length ===
                        Object.keys(products).length &&
                      selectedProductIds.length > 0
                    }
                    indeterminate={
                      selectedProductIds.length !==
                        Object.keys(products).length &&
                      selectedProductIds.length > 0
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
                      checked={selectedProductIds.includes(row.value.id)}
                      onChange={() =>
                        dispatch(Actions.toggleInSelectedProducts(row.value.id))
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
                  selectedProductIds.length > 0 && <ProductsMultiSelectMenu />,
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

export default withRouter(ProductsTable);
