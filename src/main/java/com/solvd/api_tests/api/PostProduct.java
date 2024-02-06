package com.solvd.api_tests.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.utils.config.Configuration;

public class PostProduct extends AbstractApiMethodV2 {
    public PostProduct() {
        super("api/product/post_product_rq.json", "api/product/post_product_rs.json");
        replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));

        ignorePropertiesProcessor(NotStringValuesProcessor.class);

    }
}
