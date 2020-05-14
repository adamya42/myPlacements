import axios from "axios";

export const GET_USERS = "[USER-MANAGER APP] GET USERS";
export const SET_USERS_SEARCH_TEXT = "[USER-MANAGER APP] SET USERS SEARCH TEXT";
export const TOGGLE_IN_SELECTED_USERS =
  "[USER-MANAGER APP] TOGGLE IN SELECTED USERS";

export const SELECT_ALL_USERS = "[USER-MANAGER APP] SELECT ALL USERS";
export const DESELECT_ALL_USERS = "[USER-MANAGER APP] DESELECT ALL USERS";

export const OPEN_ADD_USER_DIALOG = "[USER-MANAGER APP] OPEN_ADD_USER_DIALOG";
export const CLOSE_ADD_USER_DIALOG = "[USER-MANAGER APP] CLOSE_ADD_USER_DIALOG";

//export const REMOVE_PRODUCT = "[USER-MANAGER APP] REMOVE PRODUCT";
export const REMOVE_USERS = "[USER-MANAGER APP] REMOVE USERS";

export function getUsers() {
  const request = axios.get("http://localhost:8080/admin/api/users"); //"/api/e-commerce-app/products");

  return (dispatch) =>
    request.then((response) =>
      dispatch({
        type: GET_USERS,
        payload: response.data,
      })
    );
}

export function setUsersSearchText(event) {
  return {
    type: SET_USERS_SEARCH_TEXT,
    searchText: event.target.value,
  };
}

export function selectAllUsers() {
  return {
    type: SELECT_ALL_USERS,
  };
}

export function deSelectAllUsers() {
  return {
    type: DESELECT_ALL_USERS,
  };
}

export function toggleInSelectedUsers(userId) {
  return {
    type: TOGGLE_IN_SELECTED_USERS,
    userId,
  };
}

export function openAddUserDialog() {
  return {
    type: OPEN_ADD_USER_DIALOG,
  };
}

export function closeAddUserDialog() {
  return {
    type: CLOSE_ADD_USER_DIALOG,
  };
}

export function removeUsers(userIds) {
  return (dispatch, getState) => {
    const { routeParams } = getState().eCommerceApp.users;

    const request = axios.post(
      "http://localhost:8080/admin/api/users/delete",
      userIds
    );

    return request.then((response) =>
      Promise.all([
        dispatch({
          type: REMOVE_USERS,
        }),
        dispatch({
          type: DESELECT_ALL_USERS,
        }),
      ]).then(() => dispatch(getUsers(routeParams)))
    );
  };
}
