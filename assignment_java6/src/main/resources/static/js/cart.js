import createToast, { toastComponent } from "./toast.js"; // import toast
import { setTotalCartItemsQuantity } from "./header.js"; // import setTotalCartItemsQuantity

// STATIC DATA
const currentUserIdMetaTag = document.querySelector(
  "meta[name='currentUserId']"
); // lấy thông tin người dùng hiện tại
const deliveryMethodInputValues = {
  // lấy thông tin phương thức giao hàng
  1: {
    deliveryMethod: 1,
    deliveryPrice: 20000,
  },
  2: {
    deliveryMethod: 2,
    deliveryPrice: 50000,
  },
};

// MESSAGES
const FAILED_OPERATION_MESSAGE = "Đã có lỗi truy vấn!";
const SUCCESS_DELETE_CART_ITEM_MESSAGE = (productName) =>
  `Đã xóa sản phẩm ${productName} khỏi giỏ hàng thành công!`;
const SUCCESS_UPDATE_CART_ITEM_MESSAGE = (productName) =>
  `Đã cập nhật số lượng của sản phẩm ${productName} thành công!`;
const SUCCESS_ADD_ORDER_MESSAGE = "Đặt hàng thành công!";

// ROOTS/ELEMENTS
const cartTableRootElement = document.querySelector("#cart-table"); // lấy thông tin bảng giỏ hàng
const tempPriceRootElement = document.querySelector("#temp-price"); // lấy thông tin giá tạm tính
const deliveryPriceRootElement = document.querySelector("#delivery-price"); // lấy thông tin giá giao hàng
const totalPriceRootElement = document.querySelector("#total-price"); // lấy thông tin tổng giá
const checkoutBtnElement = document.querySelector("#checkoutBtn"); // lấy thông tin nút thanh toán
const deliveryMethodRadioElements = [
  ...document.querySelectorAll("input[name='delivery-method']"),
]; // lấy thông tin phương thức giao hàng

// COMPONENTS
function cartItemRowComponent(props) {
  // tạo component cho sản phẩm trong giỏ hàng
  const {
    id,
    cartId,
    productId,
    productName,
    productPrice,
    productDiscount,
    productQuantity,
    productImageName,
    quantity,
  } = props; // lấy thông tin sản phẩm

  return `
    <tr>
      <td>
        <figure class="itemside"> 
          <div class="float-start me-3">
            <img
              src="${"/image/" + productImageName}" 
              alt="${productName}"
              width="80"
              height="80"
            >
          </div>
          <figcaption class="info">
            <a href="${
              "/sanpham?id=" + productId
            }" class="title">${productName}</a>
          </figcaption>
        </figure>
      </td>
      <td>
        <div class="price-wrap">
          ${cartItemPriceComponent(productPrice, productDiscount)}
        </div>
      </td>
      <td>
        <input type="number" value="${quantity}" min="1" max="${productQuantity}" class="form-control" id="quantity-cart-item-${id}">
      </td>
      <td class="text-center text-nowrap">
        <button type="button" class="btn btn-success" id="update-cart-item-${id}">Cập nhật</button>
        <button type="button" class="btn btn-danger ms-1" id="delete-cart-item-${id}">Xóa</button>
      </td>
    </tr>
  `;
}

function cartItemPriceComponent(productPrice, productDiscount) {
  // tạo component cho giá sản phẩm
  if (productDiscount === 0) {
    // nếu không có giảm giá
    return `<span class="price">${_formatPrice(productPrice)}₫</span>`; // hiển thị giá
  }

  return `
    <div>
      <span class="price">${_formatPrice(
        (productPrice * (100 - productDiscount)) / 100
      )}₫</span>
      <span class="ms-2 badge bg-info">-${productDiscount}%</span>
    </div>
    <small class="text-muted text-decoration-line-through">${_formatPrice(
      productPrice
    )}₫</small>
  `; // hiển thị giá giảm giá
}

function cartTableComponent(cartItemRowComponentsFragment) {
  if (state.cart.cartItems.length === 0) {
    return `
      <div class="d-flex justify-content-center p-5 font-monospace">
        Không có sản phẩm nào trong giỏ hàng
      </div>
    `; // hiển thị thông báo khi không có sản phẩm nào trong giỏ hàng
  }

  return `
    <div class="table-responsive-xl">
      <table class="cart-table table table-borderless">
        <thead class="text-muted">
          <tr class="small text-uppercase">
            <th scope="col" style="min-width: 350px;">Sản phẩm</th>
            <th scope="col" style="min-width: 160px;">Giá</th>
            <th scope="col" style="min-width: 150px;">Số lượng</th>
            <th scope="col" style="min-width: 100px;"></th>
          </tr>
        </thead>
        <tbody>${cartItemRowComponentsFragment}</tbody>
      </table>
    </div> <!-- table.responsive-md.// -->
  `; // hiển thị bảng giỏ hàng
}

function loadingComponent() {
  return `
    <div class="d-flex justify-content-center p-5">
      <div class="spinner-border text-primary" role="status">
      <span class="visually-hidden">Đang tải...</span>
      </div>
    </div>
  `; // hiển thị thông báo đang tải
}

// UTILS
async function _fetchGetCart() {
  // lấy thông tin giỏ hàng
  const response = await fetch(
    "/cartItem?userId=" + currentUserIdMetaTag.content,
    {
      // gửi request GET để lấy thông tin giỏ hàng
      method: "GET",
      headers: {
        Accept: "application/json", // chấp nhận dữ liệu trả về dạng json
        "Content-Type": "application/json", // gửi dữ liệu dạng json
      },
    }
  );
  return [response.status, await response.json()]; // trả về status và dữ liệu json
}

async function _fetchDeleteCartItem(cartItemId, productId) {
  // xóa sản phẩm khỏi giỏ hàng
  const response = await fetch(
    "/cartItem?cartItemId=" + cartItemId + "&productId=" + productId,
    {
      // gửi request DELETE để xóa sản phẩm khỏi giỏ hàng
      method: "DELETE",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
    }
  );
  return [response.status, await response.json()]; // trả về status và dữ liệu json
}

async function _fetchUpdateCartItem(cartItemId, quantity, productId) {
  // cập nhật số lượng sản phẩm trong giỏ hàng
  const cartItemRequest = {
    // gửi request PUT để cập nhật số lượng sản phẩm trong giỏ hàng
    productId: productId,
    quantity: quantity,
  };

  const response = await fetch("/cartItem?cartItemId=" + cartItemId, {
    // gửi request PUT để cập nhật số lượng sản phẩm trong giỏ hàng
    method: "PUT",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(cartItemRequest), // chuyển dữ liệu thành dạng json
  });

  return [response.status, await response.json()]; // trả về status và dữ liệu json
}

async function _fetchPostAddOrder() { // thêm đơn hàng
  // thêm đơn hàng
  const address = document.querySelector("#diachi"); // lấy thông tin địa chỉ giao hàng
  if (address.value.length === 0) {
    createToast(toastComponent("Vui lòng nhập số địa chỉ giao hàng", "danger"));
    return;
  }
  const orderItems = state.cart.cartItems.map((cartItem) => ({
    // lấy thông tin sản phẩm trong giỏ hàng
    productId: cartItem.productId, // id sản phẩm
    price: cartItem.productPrice, // giá sản phẩm
    discount: cartItem.productDiscount, // giảm giá
    quantity: cartItem.quantity, // số lượng sản phẩm
  }));
  const orderRequest = {
    // gửi request POST để thêm đơn hàng
    cartId: state.cart.cartItems[0].cartId, // id giỏ hàng
    address: address.value, //  địa chỉ giao hàng
    userId: currentUserIdMetaTag.content, //  id người dùng
    deliveryMethod: state.order.deliveryMethod, // phương thức giao hàng
    deliveryPrice: state.order.deliveryPrice, // giá giao hàng
    orderItems: orderItems, // sản phẩm trong giỏ hàng
  };
  const response = await fetch("/cart", {
    //  gửi request POST để thêm đơn hàng
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(orderRequest), // chuyển dữ liệu thành dạng json
  });

  return [response.status, await response.json()]; // trả về status và dữ liệu json
}

function _formatPrice(price) {
  // định dạng giá
  return new Intl.NumberFormat("vi-VN").format(price.toFixed()); // định dạng giá tiền
}

// STATE
const initialCart = {
  // khởi tạo giỏ hàng người dùng hiện tại
  id: 0, // id giỏ hàng
  userId: 0, // id người dùng
  cartItems: [], // sản phẩm trong giỏ hàng
};

const initialOrder = {
  // khởi tạo đơn hàng người dùng hiện tại
  deliveryMethod: 1, // phương thức giao hàng
  deliveryPrice: 20000, // giá giao hàng
};

const state = {
  // khởi tạo state
  cart: { ...initialCart }, // giỏ hàng
  order: { ...initialOrder }, // đơn hàng
  initState: async () => {
    // khởi tạo state
    const [status, data] = await _fetchGetCart(); // lấy thông tin giỏ hàng
    if (status === 200) {
      // nếu lấy thông tin thành công
      state.cart = data; // cập nhật thông tin giỏ hàng
      render(); // render thông tin giỏ hàng
      attachEventHandlersForNoneRerenderElements(); // thêm sự kiện cho các phần tử không cần render
    } else if (status === 404) {
      createToast(toastComponent(FAILED_OPERATION_MESSAGE, "danger")); // hiển thị thông báo lỗi
    }
  },
  deleteCartItem: async (currentCartItem) => {
    // xóa sản phẩm khỏi giỏ hàng
    if (confirm("Bạn có muốn xóa?")) {
      const [status] = await _fetchDeleteCartItem(
        currentCartItem.cartId,
        currentCartItem.productId
      ); // gửi request DELETE để xóa sản phẩm khỏi giỏ hàng
      if (status === 200) {
        state.cart.cartItems = state.cart.cartItems.filter(
          (cartItem) => cartItem.id !== currentCartItem.id
        ); // cập nhật thông tin giỏ hàng
        render();
        createToast(
          toastComponent(
            SUCCESS_DELETE_CART_ITEM_MESSAGE(currentCartItem.productName),
            "success"
          )
        ); // hiển thị thông báo xóa thành công
        setTotalCartItemsQuantity(state.cart); // cập nhật tổng số sản phẩm trong giỏ hàng
      } else if (status === 404) {
        createToast(toastComponent(FAILED_OPERATION_MESSAGE, "danger"));
      }
    }
  },
  updateCartItem: async (currentCartItem, quantity) => {
    // cập nhật số lượng sản phẩm trong giỏ hàng
    if (currentCartItem.quantity !== quantity) {
      // nếu số lượng sản phẩm thay đổi
      if (quantity <= 0) {
        //
        createToast(toastComponent("Vui lòng nhập số lượng hợp lệ", "danger"));
        return;
      }
      const [status] = await _fetchUpdateCartItem(
        currentCartItem.cartId,
        quantity,
        currentCartItem.productId
      ); // gửi request PUT để cập nhật số lượng sản phẩm trong giỏ hàng
      if (status === 200) {
        state.cart.cartItems = state.cart.cartItems.map((cartItem) => {
          // cập nhật thông tin giỏ hàng
          return cartItem.id === currentCartItem.id
            ? { ...cartItem, quantity: quantity }
            : cartItem; // cập nhật số lượng sản phẩm
        });
        render();
        createToast(
          toastComponent(
            SUCCESS_UPDATE_CART_ITEM_MESSAGE(currentCartItem.productName),
            "success"
          )
        ); // hiển thị thông báo cập nhật thành công
        setTotalCartItemsQuantity(state.cart); // cập nhật tổng số sản phẩm trong giỏ hàng
      } else if (status === 404) {
        createToast(toastComponent(FAILED_OPERATION_MESSAGE, "danger"));
      }
    }
  },
  checkoutCart: async () => {
    // thanh toán giỏ hàng
    if (confirm("Bạn có muốn đặt hàng?")) {
      const [status] = await _fetchPostAddOrder(); // gửi request POST để thêm đơn hàng
      if (status === 200) {
        state.cart = { ...initialCart }; // cập nhật thông tin giỏ hàng
        state.order = { ...initialOrder }; // cập nhật thông tin đơn hàng
        render();
        createToast(toastComponent(SUCCESS_ADD_ORDER_MESSAGE, "success")); // hiển thị thông báo đặt hàng thành công
        setTotalCartItemsQuantity(state.cart); // cập nhật tổng số sản phẩm trong giỏ hàng
      } else if (status === 422) {
        createToast(toastComponent("Số lượng tồn kho không đủ", "danger")); // hiển thị thông báo số lượng tồn kho không đủ
      } else if (status === 404) {
        createToast(toastComponent(FAILED_OPERATION_MESSAGE, "danger")); // hiển thị thông báo lỗi
      }
    }
  },
  changeDeliveryMethod: (deliveryMethodValue) => {
    // thay đổi phương thức giao hàng
    if (state.order.deliveryMethod !== Number(deliveryMethodValue)) {
      // nếu phương thức giao hàng thay đổi
      state.order.deliveryMethod =
        deliveryMethodInputValues[deliveryMethodValue].deliveryMethod; // cập nhật phương thức giao hàng
      state.order.deliveryPrice =
        deliveryMethodInputValues[deliveryMethodValue].deliveryPrice; // cập nhật giá giao hàng
      render();
    }
  },
  getTempPrice: () => {
    // lấy giá tạm tính
    return state.cart.cartItems // tính giá tạm tính
      .map((cartItem) => {
        // lấy thông tin sản phẩm
        if (cartItem.productDiscount === 0) {
          // nếu không có giảm giá
          return cartItem.productPrice * cartItem.quantity; // giá sản phẩm
        }
        return (
          (
            (cartItem.productPrice * (100 - cartItem.productDiscount)) /
            100
          ).toFixed() * cartItem.quantity
        ); // giá sản phẩm giảm giá
      })
      .reduce(
        (partialSum, productTotalPrice) => partialSum + productTotalPrice,
        0
      ); // tính tổng giá tạm tính
  },
  getDeliveryPrice: () => state.order.deliveryPrice, // lấy giá giao hàng
  getTotalPrice: () => state.getTempPrice() + state.getDeliveryPrice(), // lấy tổng giá
};

// RENDER
function render() {
  // render thông tin giỏ hàng
  // Render cartTableRootElement
  const cartItemRowComponentsFragment = state.cart.cartItems
    .map(cartItemRowComponent)
    .join(""); // tạo component cho sản phẩm trong giỏ hàng
  cartTableRootElement.innerHTML = cartTableComponent(
    cartItemRowComponentsFragment
  ); // hiển thị thông tin giỏ hàng

  // Render tempPriceRootElement, deliveryPriceRootElement, totalPriceRootElement
  tempPriceRootElement.innerHTML = _formatPrice(state.getTempPrice()); // hiển thị giá tạm tính
  deliveryPriceRootElement.innerHTML = _formatPrice(state.getDeliveryPrice()); // hiển thị giá giao hàng
  totalPriceRootElement.innerHTML = _formatPrice(state.getTotalPrice()); // hiển thị tổng giá

  // Render checkoutBtnElement, deliveryMethodRadioElements
  const isCartItemsEmpty = state.cart.cartItems.length === 0; // kiểm tra giỏ hàng có sản phẩm không
  checkoutBtnElement.disabled = isCartItemsEmpty; // kiểm tra nút thanh toán có bị vô hiệu hóa không
  deliveryMethodRadioElements.forEach((radio) => {
    // kiểm tra phương thức giao hàng
    radio.disabled = isCartItemsEmpty; // kiểm tra phương thức giao hàng có bị vô hiệu hóa không
    radio.checked = radio.value === String(state.order.deliveryMethod); // kiểm tra phương thức giao hàng có được chọn không
  });

  // Attach event handlers for delete cart item buttons
  state.cart.cartItems.forEach((cartItem) => {
    // thêm sự kiện cho nút xóa sản phẩm khỏi giỏ hàng
    const deleteCartItemBtnElement = document.querySelector(
      `#delete-cart-item-${cartItem.id}`
    ); // lấy thông tin nút xóa sản phẩm khỏi giỏ hàng
    deleteCartItemBtnElement.addEventListener("click", () =>
      state.deleteCartItem(cartItem)
    ); //  thêm sự kiện click vào nút xóa sản phẩm khỏi giỏ hàng
  });

  // Attach event handlers for update cart item buttons
  state.cart.cartItems.forEach((cartItem) => {
    // thêm sự kiện cho nút cập nhật số lượng sản phẩm trong giỏ hàng
    const updateCartItemBtnElement = document.querySelector(
      `#update-cart-item-${cartItem.id}`
    ); // lấy thông tin nút cập nhật số lượng sản phẩm trong giỏ hàng
    updateCartItemBtnElement.addEventListener("click", () => {
      // thêm sự kiện click vào nút cập nhật số lượng sản phẩm trong giỏ hàng
      const quantityCartItemInputElement = document.querySelector(
        `#quantity-cart-item-${cartItem.id}`
      ); // lấy thông tin số lượng sản phẩm
      void state.updateCartItem(
        cartItem,
        Number(quantityCartItemInputElement.value)
      ); // cập nhật số lượng sản phẩm
    });
  });
}

function attachEventHandlersForNoneRerenderElements() {
  // thêm sự kiện cho các phần tử không cần render
  // Attach event handlers for delivery method radios
  deliveryMethodRadioElements.forEach((radio) => {
    // thêm sự kiện cho phương thức giao hàng
    radio.addEventListener("click", () =>
      state.changeDeliveryMethod(radio.value)
    ); // thêm sự kiện click vào phương thức giao hàng
  });

  // Attach event handlers for checkout button
  checkoutBtnElement.addEventListener("click", state.checkoutCart); // thêm sự kiện click vào nút thanh toán
}

// MAIN
if (currentUserIdMetaTag) {
  // kiểm tra người dùng đã đăng nhập chưa
  cartTableRootElement.innerHTML = loadingComponent(); // hiển thị thông báo đang tải chờ
  void state.initState(); // khởi tạo state
}
