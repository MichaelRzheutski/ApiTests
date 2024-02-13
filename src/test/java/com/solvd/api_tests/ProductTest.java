package com.solvd.api_tests;

import com.solvd.api_tests.api.product.*;
import com.solvd.api_tests.domain.Product;
import com.zebrunner.carina.api.apitools.validation.JsonComparatorContext;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

public class ProductTest {
    @DataProvider(name = "validProductValues")
    public Object[][] validProductValues() {
        return new Object[][] {
                {
                    new Product(
                            1,
                            "iPhone 9",
                            "An apple mobile which is nothing like apple",
                            549,
                            12.96,
                            4.69,
                            94,
                            "Apple",
                            "smartphones"
                    )
                }
        };
    }

    @Test(description = "Verify getting product by id", dataProvider = "validProductValues")
    public void verifyGetProductByIdTest(Product product) {
        GetProductById getProductById = new GetProductById(product.getId());
        getProductById.addProperty("product", product);

        getProductById.expectResponseStatus(HttpResponseStatusType.OK_200);
        getProductById.callAPI();

        JsonComparatorContext comparatorContext = JsonComparatorContext.context()
                .<Integer>withPredicate("idPredicate", id -> Objects.equals(id, product.getId()))
                .<Integer>withPredicate("pricePredicate", price -> Objects.equals(price, product.getPrice()))
                .<Double>withPredicate("discountPercentagePredicate", discountPercentage -> Objects.equals(discountPercentage, product.getDiscountPercentage()))
                .<Double>withPredicate("ratingPredicate", rating -> Objects.equals(rating, product.getRating()))
                .<Integer>withPredicate("stockPredicate",stock -> Objects.equals(stock, product.getStock()));

        getProductById.validateResponse(comparatorContext);
    }

    @Test(description = "Verify creating product", dataProvider = "validProductValues")
    public void verifyPostProductTest(Product product) {
        PostProduct postProduct = new PostProduct();
        postProduct.addProperty("product", product);
        postProduct.expectResponseStatus(HttpResponseStatusType.OK_200);
        postProduct.callAPI();
        postProduct.validateResponse();
    }

    @Test(description = "Verify deleting product by id", dataProvider = "validProductValues")
    public void verifyDeleteProductByIdTest(Product product) {
        DeleteProductById deleteProductById = new DeleteProductById(product.getId());
        deleteProductById.addProperty("product", product);
        deleteProductById.callAPIExpectSuccess();

        JsonComparatorContext comparatorContext = JsonComparatorContext.context()
                .<Integer>withPredicate("idPredicate", id -> Objects.equals(id, product.getId()))
                .<Integer>withPredicate("pricePredicate", price -> Objects.equals(price, product.getPrice()))
                .<Double>withPredicate("discountPercentagePredicate", discountPercentage -> Objects.equals(discountPercentage, product.getDiscountPercentage()))
                .<Double>withPredicate("ratingPredicate", rating -> Objects.equals(rating, product.getRating()))
                .<Integer>withPredicate("stockPredicate",stock -> Objects.equals(stock, product.getStock()))
                .<String>withPredicate("datePredicate", date -> isDateValid(date) && ZonedDateTime.parse(date).isAfter(LocalDate.of(2024, 1, 1).atStartOfDay(ZoneId.systemDefault())))
                .<Boolean>withPredicate("isDeletedPredicate", isDeleted -> isDeleted);

        deleteProductById.validateResponse(comparatorContext);
    }

    private static boolean isDateValid(String date) {
        try {
            ZonedDateTime.parse(date);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    @Test(description = "Verify updating product by id", dataProvider = "validProductValues")
    public void verifyPutProductByIdTest(Product product) {
        PutProductById putProductById = new PutProductById(product.getId());
        product.setPrice(999);
        putProductById.addProperty("product", product);
        putProductById.callAPIExpectSuccess();

        JsonComparatorContext comparatorContext = JsonComparatorContext.context()
                .<Integer>withPredicate("idPredicate", id -> Objects.equals(id, product.getId()));

        putProductById.validateResponse(comparatorContext);
    }
}
