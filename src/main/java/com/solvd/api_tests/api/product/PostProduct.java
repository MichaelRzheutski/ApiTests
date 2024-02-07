package com.solvd.api_tests.api.product;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.api.http.HttpMethodType;

@Endpoint(url = "${config.api_url}/products/add", methodType = HttpMethodType.POST)
@RequestTemplatePath(path = "api/product/post_product_request.json")
@ResponseTemplatePath(path = "api/product/post_product_response.json")
public class PostProduct extends AbstractApiMethodV2 {
    public PostProduct() {
        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}
