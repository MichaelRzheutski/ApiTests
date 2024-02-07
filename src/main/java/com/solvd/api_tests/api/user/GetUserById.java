package com.solvd.api_tests.api.user;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.api.http.HttpMethodType;

@Endpoint(url = "${config.api_url}/users/${id}", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/user/get_user_response.json")
public class GetUserById extends AbstractApiMethodV2 {
    public GetUserById(int id) {
        replaceUrlPlaceholder("id", String.valueOf(id));
        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}
