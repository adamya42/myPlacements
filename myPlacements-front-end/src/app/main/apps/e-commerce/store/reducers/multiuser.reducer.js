import * as Actions from "../actions";

const initialState = {
  data: [],
  routeParams: {},
};

const multiUserReducer = function (state = initialState, action) {
  switch (action.type) {
    case Actions.ADD_MULTIPLE_USERS: {
      return {
        ...state,
        data: action.payload,
      };
    }
    default: {
      return state;
    }
  }
};

export default multiUserReducer;
