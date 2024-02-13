package com.solvd.api_tests.api.product;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.api.http.HttpMethodType;

@Endpoint(url = "${config.api_url}/products/${id}", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/product/get_product_response.json")
public class GetProductById extends AbstractApiMethodV2 {
    public GetProductById(int id) {
        replaceUrlPlaceholder("id", String.valueOf(id));
        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}
