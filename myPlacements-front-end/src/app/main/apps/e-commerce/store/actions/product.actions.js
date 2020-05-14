import axios from "axios";
import { showMessage } from "app/store/actions/fuse";
import history from "@history";

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

export function saveUser(data) {
  const request = axios.post("http://localhost:8080/user/updateUser", data);

  return (dispatch) => {
    request.then((response) => {
      try {
        dispatch(showMessage({ message: "Product Saved" }));

        return dispatch(
          // {
          //   type: SAVE_PRODUCT,
          //   payload: response.data,
          // },
          getProduct(data.id)
        );
        // return dispatch;
      } catch (error) {
        dispatch(showMessage({ message: response.data }));
      }
    });
  };
}

export function addSingleUser(addUser) {
  return (dispatch) => {
    const request = axios.post(
      "http://localhost:8080/admin/api/addSingleUser",
      addUser
    );

    return request.then((response) => {
      if (response.status > 199 && response.status < 299) {
        dispatch(showMessage({ message: response.data }));
        history.push("/apps/user-manager");

        return dispatch({
          type: ADD_SINGLE_USER,
          payload: response.data,
        });
      } else {
        dispatch(showMessage({ message: "alredy available" }));
      }
    });
  };
}

// return request.then((response) => {
//   try {
//     Promise.all([
//       dispatch({
//         type: ADD_SINGLE_USER,
//       }),
//     ]).then(() => dispatch(getProducts()));
//     {
//       props.history.push("/apps/user-manager/users");
//     }
//   } catch (error) {
//     dispatch(showMessage({ message: "alredy available" }));
//   }
// });
