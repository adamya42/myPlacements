import * as Actions from "../actions";

const initialState = {
  data: [],
  searchText: "",
  selectedUserIds: [],
  routeParams: {},
  addUserDialog: {
    type: "new",
    props: {
      open: false,
    },
    data: {},
  },
};

const usersReducer = function (state = initialState, action) {
  switch (action.type) {
    case Actions.GET_USERS: {
      return {
        ...state,
        data: action.payload,
      };
    }
    case Actions.SET_USERS_SEARCH_TEXT: {
      return {
        ...state,
        searchText: action.searchText,
      };
    }
    case Actions.SELECT_ALL_USERS: {
      const arr = Object.keys(state.data).map((k) => state.data[k]);

      const selectedUserIds = arr.map((user) => user.id);

      return {
        ...state,
        selectedUserIds: selectedUserIds,
      };
    }
    case Actions.DESELECT_ALL_USERS: {
      return {
        ...state,
        selectedUserIds: [],
      };
    }
    case Actions.TOGGLE_IN_SELECTED_USERS: {
      const userId = action.userId;

      let selectedUserIds = [...state.selectedUserIds];

      if (selectedUserIds.find((id) => id === userId) !== undefined) {
        selectedUserIds = selectedUserIds.filter((id) => id !== userId);
      } else {
        selectedUserIds = [...selectedUserIds, userId];
      }

      return {
        ...state,
        selectedUserIds: selectedUserIds,
      };
    }

    case Actions.OPEN_ADD_USER_DIALOG: {
      return {
        ...state,
        addUserDialog: {
          type: "new",
          props: {
            open: true,
          },
          data: [],
        },
      };
    }
    case Actions.CLOSE_ADD_USER_DIALOG: {
      return {
        ...state,
        addUserDialog: {
          type: "new",
          props: {
            open: false,
          },
          data: [],
        },
      };
    }
    case Actions.ADD_SINGLE_USER: {
      return {
        ...state,
        addUserDialog: {
          type: "new",
          props: {
            open: false,
          },
          data: action.payload,
        },
      };
    }
    default: {
      return state;
    }
  }
};

export default usersReducer;
