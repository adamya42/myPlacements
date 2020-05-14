import axios from "axios";
import { showMessage } from "app/store/actions/fuse";
import history from "@history";

export const GET_USER = "[USER-MANAGER APP] GET USER";
export const SAVE_USER = "[USER-MANAGER APP] SAVE USER";
export const ADD_SINGLE_USER = "[USER-MANAGER APP] ADD SINGLE USER";

export function getUser(params) {
  //const request = axios.get("/api/e-commerce-app/product", { params });
  const request = axios.get("http://localhost:8080/admin/api/users/" + params);
  return (dispatch) =>
    request.then((response) =>
      dispatch({
        type: GET_USER,
        payload: response.data,
      })
    );
}

export function saveUser(data) {
  const request = axios.post("http://localhost:8080/user/updateUser", data);

  return (dispatch) => {
    request.then((response) => {
      try {
        dispatch(showMessage({ message: "User Saved" }));

        return dispatch(
          // {
          //   type: SAVE_USER,
          //   payload: response.data,
          // },
          getUser(data.id)
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
