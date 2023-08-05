package com.app.tourist.ui.view.signup;

import androidx.annotation.Nullable;

import com.app.tourist.ui.view.login.LoggedInUserView;

public class SignupResult {
    @Nullable
    private LoggedInUserView success;
    @Nullable
    private Integer error;
    @Nullable
    private String errorMessage;

    SignupResult(@Nullable Integer error) {
        this.error = error;
    }

    SignupResult(@Nullable String message) {
        this.errorMessage = message;
    }

    SignupResult(@Nullable LoggedInUserView success) {
        this.success = success;
    }

    @Nullable
    LoggedInUserView getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }

    @Nullable
    String getErrorMessage(){return errorMessage;}
}
