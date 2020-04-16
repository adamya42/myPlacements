import * as Actions from "../actions";

const initialState = {
  data: [],
  searchText: "",
  selectedProductIds: [],
  routeParams: {},
};

const productsReducer = function (state = initialState, action) {
  switch (action.type) {
    case Actions.GET_PRODUCTS: {
      return {
        ...state,
        data: action.payload,
      };
    }
    case Actions.SET_PRODUCTS_SEARCH_TEXT: {
      return {
        ...state,
        searchText: action.searchText,
      };
    }
    case Actions.SELECT_ALL_PRODUCTS: {
      const arr = Object.keys(state.data).map((k) => state.data[k]);

      const selectedProductIds = arr.map((product) => product.id);

      return {
        ...state,
        selectedProductIds: selectedProductIds,
      };
    }
    case Actions.DESELECT_ALL_PRODUCTS: {
      return {
        ...state,
        selectedProductIds: [],
      };
    }
    case Actions.TOGGLE_IN_SELECTED_PRODUCTS: {
      const productId = action.productId;

      let selectedProductIds = [...state.selectedProductIds];

      if (selectedProductIds.find((id) => id === productId) !== undefined) {
        selectedProductIds = selectedProductIds.filter(
          (id) => id !== productId
        );
      } else {
        selectedProductIds = [...selectedProductIds, productId];
      }

      return {
        ...state,
        selectedProductIds: selectedProductIds,
      };
    }
    default: {
      return state;
    }
  }
};

export default productsReducer;
