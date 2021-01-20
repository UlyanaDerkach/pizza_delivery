// function findTotalSum(){
//     let productAmount = document.getElementsByName('productAmount');
//     let productPrice = document.getElementsByName('productPrice');
//     let totalSum =0;
//     let totalSumForProduct;
//     for(let i=0;i< productAmount.length; i++){
//         totalSumForProduct = parseInt(productAmount[i].value) * parseInt(productPrice[i].value);
//         if(!isNaN(totalSumForProduct)){
//             totalSum += totalSumForProduct;
//         }
//
//     }
//     document.getElementById('totalSum').value = totalSum;
// }
$('.cart-variant--quantity_input').on("change", function () {
    var st = 0;
    $('.cart-row').each(function () {
        var i = $('.cart-variant--quantity_input', this);
        var up = $(i).data('unit-price');
        var q = $(i).val();
        st = st + (up * q);
    });
    // Subtotal price
    document.getElementById('totalSum').value = st;
});
