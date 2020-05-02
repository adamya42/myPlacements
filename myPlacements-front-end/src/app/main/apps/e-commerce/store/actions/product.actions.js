import axios from "axios";
import { FuseUtils } from "@fuse";
import { showMessage } from "app/store/actions/fuse";
import { getProducts } from "./products.actions";

export const GET_PRODUCT = "[E-COMMERCE APP] GET PRODUCT";
export const SAVE_PRODUCT = "[E-COMMERCE APP] SAVE PRODUCT";
export const ADD_SINGLE_USER = "[E-COMMERCE APP] ADD SINGLE USER";

export function getProduct(params) {
  //const request = axios.get("/api/e-commerce-app/product", { params });
  const request = axios.get("http://localhost:8080/admin/api/users/" + params);
  return (dispatch) =>
    request.then((response) =>
      dispatch({
        type: GET_PRODUCT,
        payload: response.data,
      })
    );
}

export function saveProduct(data) {
  const request = axios.post("/api/e-commerce-app/product/save", data);

  return (dispatch) =>
    request.then((response) => {
      dispatch(showMessage({ message: "Product Saved" }));

      return dispatch({
        type: SAVE_PRODUCT,
        payload: response.data,
      });
    });
}

export function addSingleUser(addUser) {
  return (dispatch, getState) => {
    const { routeParams } = getState().eCommerceApp.products;

    const request = axios.post(
      "http://localhost:8080/admin/api/addSingleUser",
      addUser
    );

    return request.then((response) =>
      Promise.all([
        dispatch({
          type: ADD_SINGLE_USER,
        }),
      ]).then(() => dispatch(getProducts(routeParams)))
    );
  };
}
