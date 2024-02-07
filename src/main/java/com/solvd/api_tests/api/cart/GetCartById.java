package com.solvd.api_tests.api.cart;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.api.http.HttpMethodType;

@Endpoint(url = "${config.api_url}/carts/${id}", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/cart/get_cart_response.json")
public class GetCartById extends AbstractApiMethodV2 {
    public GetCartById(int id) {
        replaceUrlPlaceholder("id", String.valueOf(id));
        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}
