package com.solvd.api_tests;

import com.solvd.api_tests.api.cart.GetCartById;
import com.solvd.api_tests.domain.Cart;
import com.solvd.api_tests.domain.Product;
import com.zebrunner.carina.api.apitools.validation.JsonComparatorContext;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import org.json.JSONArray;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Objects;

public class CartTest {
    @DataProvider(name = "validCartValues")
    public Object[][] validCartValues() {
        return new Object[][]{
                {
                        new Cart(
                                1,
                                new Product[5],
                                2328,
                                1941,
                                97,
                                5,
                                10
                        )
                }
        };
    }

    @Test(description = "Verify getting cart by id", dataProvider = "validCartValues")
    public void verifyGetCartById(Cart cart) {
        GetCartById getCartById = new GetCartById(cart.getId());
        getCartById.addProperty("cart", cart);

        getCartById.expectResponseStatus(HttpResponseStatusType.OK_200);
        getCartById.callAPI();

        JsonComparatorContext comparatorContext = JsonComparatorContext.context()
                .<Integer>withPredicate("idPredicate", id -> Objects.equals(id, cart.getId()))
                .<Integer>withPredicate("totalPredicate", total -> Objects.equals(total, cart.getTotal()))
                .<Integer>withPredicate("discountedTotalPredicate", discountedTotal -> Objects.equals(discountedTotal, cart.getDiscountedTotal()))
                .<Integer>withPredicate("userIdPredicate", userId -> Objects.equals(userId, cart.getUserId()))
                .<Integer>withPredicate("totalProductsPredicate", totalProducts -> Objects.equals(totalProducts, cart.getTotalProducts()))
                .<Integer>withPredicate("totalQualityPredicate", totalQuality -> Objects.equals(totalQuality, cart.getTotalQuantity()))
                .<JSONArray>withPredicate("productsPredicate", products -> Objects.equals(products.length(), cart.getProducts().length));

        getCartById.validateResponse(comparatorContext);
    }
}
