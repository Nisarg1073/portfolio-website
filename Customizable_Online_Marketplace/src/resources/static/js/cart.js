let cart = [];

function addToCart(product) {
    cart.push(product);
    alert(`${product.name} added to cart!`);
}

function checkout() {
    if (cart.length === 0) {
        alert("Your cart is empty.");
        return;
    }
    alert("Proceeding to checkout...");
}
