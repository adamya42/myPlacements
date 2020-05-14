import axios from "axios";
import { showMessage } from "app/store/actions";

export const ADD_MULTIPLE_USERS = "[E-COMMERCE APP] ADD MULTIPLE USERS";

export function addMultipleUsers(addUser) {
  return (dispatch) => {
    const request = axios.post(
      "http://localhost:8080/admin/api/addMultipleUsers",
      addUser
    );

    return request.then((response) => {
      if (response.status > 199 && response.status < 299) {
        dispatch(showMessage({ message: response.data }));

        return dispatch({
          type: ADD_MULTIPLE_USERS,
          payload: response.data,
        });
      } else {
        dispatch(showMessage({ message: "alredy available" }));
      }
    });
  };
}
