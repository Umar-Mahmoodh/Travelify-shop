if (document.readyState == 'loading') {
    document.addEventListener('DOMContentLoaded', ready)
} else {
    ready()
}

function ready() {

    // Removing the cart items with the remove button
    var removeCartItemButtons = document.getElementsByClassName('btn-danger')
    for (var i = 0; i < removeCartItemButtons.length; i++) {
        var button = removeCartItemButtons[i]
        button.addEventListener('click', removeCartItem)
    }

    // changing the Quantity value
    var quantityInputs = document.getElementsByClassName('cart-quantity-input')
    for (var i = 0; i < quantityInputs.length; i++) {
        var input = quantityInputs[i]
        input.addEventListener('change', quantityChanged)
    }

    var addToCartButtons = document.getElementsByClassName('purchase-btn')
    for (var i = 0; i < addToCartButtons.length; i++) {
        var button = addToCartButtons[i]
        button.addEventListener('click', addToCartClicked)
    }

    document.getElementsByClassName('btn-purchase')[0].addEventListener('click', purchaseClicked)
}


// Function for purchase button
function purchaseClicked() {
    alert('Thank you for your purchase')
    document.getElementById('myModal').style.display=""

    var itemArray = new Array()
    var quantityArray = new Array()
    var priceArray = new Array()
    var outputArray = new Array()
    
    var cartList = document.getElementsByClassName('shop-item-title')[0]
    var cartListTitle = cartList.getElementsByClassName('shop-item-image')
    

    
    
        for (var i = 0; i < cartListTitle.length; i++ ){
            var productName = cartListTitle[i].innerText
            itemArray[i] = " " + productName
    
            var itemQuantity = cartList.getElementsByClassName('cart-quantity-input')
            var quantityValue = itemQuantity[i].value
            quantityArray[i] = " "+ quantityValue
    
            var cartListPrice = cartList.getElementsByClassName('cart-price')
            priceArray[i] = " " + cartListPrice[i].innerText
    
    
            outputArray[i] =  quantityArray[i] + " " + itemArray[i] +"(s)" + " " + "of each" + " " + priceArray[i] + "\n"

            console.log(outputArray[i])
            document.getElementById('displaypname').innerHTML = outputArray[i];

       }

    var cartItems = document.getElementsByClassName('cart-items')[0]
    while (cartItems.hasChildNodes()) {
        cartItems.removeChild(cartItems.firstChild)
    }
    updateCartTotal()


    
}


// Function to close the pop up modal
function close(){
    document.getElementById('myModal').style.display="none"
}


// Function to remove items
function removeCartItem(event) {
    var buttonClicked = event.target
    buttonClicked.parentElement.parentElement.remove()
    updateCartTotal()
}

// Function to change the Quantity value
function quantityChanged(event) {
    var input = event.target
    if (isNaN(input.value) || input.value <= 0) {
        input.value = 1
    }
    updateCartTotal()
}
var title ="shop-item-title"; 


// Function for adding items to the cart
function addToCartClicked(event) {
    var button = event.target
    var shopItem = button.parentElement.parentElement
    title = shopItem.getElementsByClassName('shop-item-title')[0].innerText
    var price = shopItem.getElementsByClassName('cart-price')[0].innerText
    var imageSrc = shopItem.getElementsByClassName('shop-item-image')[0].src
    addItemToCart(title, price, imageSrc)
    updateCartTotal()
}

//Function for adding new items to the cart
function addItemToCart(title, price, imageSrc) {
    var cartRow = document.createElement('div')
    cartRow.classList.add('cart-row')
    var cartItems = document.getElementsByClassName('cart-items')[0]
    var cartItemNames = cartItems.getElementsByClassName('cart-item-title')
    for (var i = 0; i < cartItemNames.length; i++) {
        if (cartItemNames[i].innerText == title) {
            alert('This item is already added to the cart')
            return
        }
    }
    var cartRowContents = `
        <div class="cart-item cart-column">
            <img class="cart-item-image" src="${imageSrc}" width="100" height="100">
            <span class="cart-item-title">${title}</span>
        </div>
        <span class="cart-price cart-column">${price}</span>
        <div class="cart-quantity cart-column">
            <input class="cart-quantity-input" type="number" value="1">
            <button class="btn btn-danger" type="button">REMOVE</button>
        </div>`
    cartRow.innerHTML = cartRowContents
    cartItems.append(cartRow)
    cartRow.getElementsByClassName('btn-danger')[0].addEventListener('click', removeCartItem)
    cartRow.getElementsByClassName('cart-quantity-input')[0].addEventListener('change', quantityChanged)
}

// Function for updating the cart total
function updateCartTotal() {
    var cartItemContainer = document.getElementsByClassName('cart-items')[0]
    var cartRows = cartItemContainer.getElementsByClassName('cart-row')
    var total = 0
    for (var i = 0; i < cartRows.length; i++) {
        var cartRow = cartRows[i]
        var priceElement = cartRow.getElementsByClassName('cart-price')[0]
        var quantityElement = cartRow.getElementsByClassName('cart-quantity-input')[0]
        var price = parseFloat(priceElement.innerText.replace('$', ''))
        var quantity = quantityElement.value
        total = total + (price * quantity)
    }
    total = Math.round(total * 100) / 100
    document.getElementsByClassName('cart-total-price')[0].innerText = '$' + total
}

// Function for displaying invoice
function displayInfo(){
    var fname = document.forms["cart-form"]["fname"].value
    var lname = document.forms["cart-form"]["lname"].value
    var mnum = document.forms["cart-form"]["mnum"].value
    var email = document.forms["cart-form"]["email"].value
    var address = document.forms["cart-form"]["address"].value
    var city = document.forms["cart-form"]["city"].value
    var country = document.forms["cart-form"]["country"].value
    var zipcode = document.forms["cart-form"]["zipcode"].value

    document.getElementById("displayfname").innerHTML=fname
    document.getElementById("displaylname").innerHTML=lname
    document.getElementById("displaymnum").innerHTML=mnum
    document.getElementById("displayemail").innerHTML=email
    document.getElementById("displayaddress").innerHTML=address
    document.getElementById("displaycity").innerHTML=city
    document.getElementById("displaycountry").innerHTML=country
    document.getElementById("displayzipcode").innerHTML=zipcode

    document.getElementById("displaypname").innerHTML= title 
    document.getElementById("test").innerHTML= "product" + title 


}


