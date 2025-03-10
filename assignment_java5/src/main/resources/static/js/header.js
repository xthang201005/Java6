// STATIC DATA
const currentUserIdMetaTag = document.querySelector("meta[name='currentUserId']");

// ROOTS/ELEMENTS
const totalCartItemsQuantityRootElement = document.querySelector("#total-cart-items-quantity");

// UTILS
async function _fetchGetCart() {
  const response = await fetch("/cartItem?userId=" + currentUserIdMetaTag.content, {
    method: "GET",
    headers: {
      "Accept": "application/json",
      "Content-Type": "application/json",
    },
  });
  return [response.status, await response.json()];
}

// STATE
const state = {// trạng thái
  totalCartItemsQuantity: 0, // tổng số lượng sản phẩm trong giỏ hàng
  setTotalCartItemsQuantity: (value) => { // cập nhật tổng số lượng sản phẩm trong giỏ hàng
    if (typeof value === "string") {
      state.totalCartItemsQuantity += Number(value); // cập nhật số lượng sản phẩm
    } else {
      state.totalCartItemsQuantity = value.cartItems  // cập nhật số lượng sản phẩm
        .map((cartItem) => cartItem.quantity) // lấy số lượng sản phẩm
        .reduce((partialSum, cartItemQuantity) => partialSum + cartItemQuantity, 0); // tính tổng số lượng sản phẩm
    }
    render();
  },
  initState: async () => { // khởi tạo trạng thái
    const [status, data] = await _fetchGetCart(); // lấy thông tin giỏ hàng
    if (status === 200) {
      state.setTotalCartItemsQuantity(data); // cập nhật tổng số lượng sản phẩm trong giỏ hàng
    }
  },
}

// RENDER
function render() {
  totalCartItemsQuantityRootElement.innerHTML = state.totalCartItemsQuantity; // hiển thị tổng số lượng sản phẩm trong giỏ hàng
}

// MAIN
if (currentUserIdMetaTag) {
  void state.initState(); // khởi tạo trạng thái nếu tồn tại người dùng
}

export const setTotalCartItemsQuantity = state.setTotalCartItemsQuantity; // xuất hàm cập nhật tổng số lượng sản phẩm trong giỏ hàng
