package com.solvd.api_tests.api.user;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.api.http.HttpMethodType;

@Endpoint(url = "${config.api_url}/users/add", methodType = HttpMethodType.POST)
@RequestTemplatePath(path = "api/user/post_user_request.json")
@ResponseTemplatePath(path = "api/user/post_user_response.json")
public class PostUser extends AbstractApiMethodV2 {
    public PostUser() {
        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }
}
