import axios from "axios";

export const GET_PRODUCTS = "[E-COMMERCE APP] GET PRODUCTS";
export const SET_PRODUCTS_SEARCH_TEXT =
  "[E-COMMERCE APP] SET PRODUCTS SEARCH TEXT";
export const TOGGLE_IN_SELECTED_PRODUCTS =
  "[E-COMMERCE APP] TOGGLE IN SELECTED PRODUCTS";

export const SELECT_ALL_PRODUCTS = "[E-COMMERCE APP] SELECT ALL PRODUCTS";
export const DESELECT_ALL_PRODUCTS = "[E-COMMERCE APP] DESELECT ALL PRODUCTS";

export const REMOVE_PRODUCT = "[E-COMMERCE APP] REMOVE PRODUCT";
export const REMOVE_PRODUCTS = "[E-COMMERCE APP] REMOVE PRODUCTS";

export function getProducts() {
  const request = axios.get("http://localhost:8080/admin/api/users"); //"/api/e-commerce-app/products");

  return (dispatch) =>
    request.then((response) =>
      dispatch({
        type: GET_PRODUCTS,
        payload: response.data,
      })
    );
}

export function setProductsSearchText(event) {
  return {
    type: SET_PRODUCTS_SEARCH_TEXT,
    searchText: event.target.value,
  };
}

export function selectAllProducts() {
  return {
    type: SELECT_ALL_PRODUCTS,
  };
}

export function deSelectAllProducts() {
  return {
    type: DESELECT_ALL_PRODUCTS,
  };
}

export function toggleInSelectedProducts(productId) {
  return {
    type: TOGGLE_IN_SELECTED_PRODUCTS,
    productId,
  };
}

export function removeProducts(productIds) {
  return (dispatch, getState) => {
    const { routeParams } = getState().eCommerceApp.products;

    const request = axios.post("/api/contacts-app/remove-contacts", {
      productIds,
    });

    return request.then((response) =>
      Promise.all([
        dispatch({
          type: REMOVE_PRODUCTS,
        }),
        dispatch({
          type: DESELECT_ALL_PRODUCTS,
        }),
      ]).then(() => dispatch(getProducts(routeParams)))
    );
  };
}
