package com.app.tourist.ui.view.login;

import androidx.annotation.Nullable;

public class LoginResult {
    @Nullable
    private LoggedInUserView success;
    @Nullable
    private Integer error;
    @Nullable
    private String errorMessage;

    LoginResult(@Nullable Integer error) {
        this.error = error;
    }

    LoginResult(@Nullable String message) {
        this.errorMessage = message;
    }

    LoginResult(@Nullable LoggedInUserView success) {
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
