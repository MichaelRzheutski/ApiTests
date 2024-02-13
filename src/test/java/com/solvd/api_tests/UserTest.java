package com.solvd.api_tests;

import com.solvd.api_tests.api.user.DeleteUserById;
import com.solvd.api_tests.api.user.GetUserById;
import com.solvd.api_tests.api.user.PostUser;
import com.solvd.api_tests.domain.User;
import com.zebrunner.carina.api.apitools.validation.JsonComparatorContext;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

public class UserTest {
    @DataProvider(name = "validUserValues")
    public Object[][] validUserValues() {
        return new Object[][]{
                {
                        new User(
                                1,
                                "Terry",
                                "Medhurst",
                                "Smitham",
                                50,
                                "male"
                        )
                }
        };
    }

    @Test(description = "Verify getting user by id", dataProvider = "validUserValues")
    public void verifyGetUserByIdTest(User user) {
        GetUserById getUserById = new GetUserById(user.getId());
        getUserById.addProperty("user", user);

        getUserById.expectResponseStatus(HttpResponseStatusType.OK_200);
        getUserById.callAPI();
    }

    @Test(description = "Verify creating user", dataProvider = "validUserValues")
    public void verifyPostProductTest(User user) {
        PostUser postUser = new PostUser();
        postUser.addProperty("user", user);
        postUser.expectResponseStatus(HttpResponseStatusType.OK_200);
        postUser.callAPI();
//        postUser.validateResponse();
    }

    @Test(description = "Verify deleting user by id", dataProvider = "validUserValues")
    public void verifyDeleteUserByIdTest(User user) {
        DeleteUserById deleteUserById = new DeleteUserById(user.getId());
        deleteUserById.addProperty("user", user);
        deleteUserById.callAPIExpectSuccess();

        JsonComparatorContext comparatorContext = JsonComparatorContext.context()
                .<Integer>withPredicate("idPredicate", id -> Objects.equals(id, user.getId()))
                .<Integer>withPredicate("agePredicate", id -> Objects.equals(id, user.getAge()))
                .<String>withPredicate("datePredicate", date -> isDateValid(date) && ZonedDateTime.parse(date).isAfter(LocalDate.of(2024, 1, 1).atStartOfDay(ZoneId.systemDefault())))
                .<Boolean>withPredicate("isDeletedPredicate", isDeleted -> isDeleted);

        deleteUserById.validateResponse(comparatorContext);
    }

    private static boolean isDateValid(String date) {
        try {
            ZonedDateTime.parse(date);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }
}
