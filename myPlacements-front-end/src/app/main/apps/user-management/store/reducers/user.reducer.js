import * as Actions from "../actions";

const initialState = {
  data: [],

  routeParams: {},
};

const userReducer = function (state = initialState, action) {
  switch (action.type) {
    case Actions.GET_USER: {
      return {
        ...state,
        data: action.payload,
      };
    }
    case Actions.SAVE_USER: {
      return {
        ...state,
        data: action.payload,
      };
    }
    // case Actions.ADD_SINGLE_USER: {
    //   return {
    //     ...state,
    //     data: action.payload,
    //   };
    // }

    default: {
      return state;
    }
  }
};

export default userReducer;
