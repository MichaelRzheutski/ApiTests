package com.solvd.api_tests.api.user;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.utils.config.Configuration;

public class PostUser extends AbstractApiMethodV2 {
    public PostUser() {
        super("api/user/post_user_request.json", "api/user/post_user_response.json");
        replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));

        ignorePropertiesProcessor(NotStringValuesProcessor.class);

    }
}
