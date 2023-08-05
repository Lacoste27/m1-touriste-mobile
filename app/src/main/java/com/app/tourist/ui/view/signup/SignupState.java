package com.app.tourist.ui.view.signup;

import androidx.annotation.Nullable;

public class SignupState {
    @Nullable
    private Integer nomError;
    @Nullable
    private Integer prenomError;
    @Nullable
    private Integer usernameError;
    @Nullable
    private Integer passwordError;
    private boolean isDataValid;

    SignupState(
            @Nullable Integer nomError,
            @Nullable Integer prenomError,
            @Nullable Integer usernameError,
            @Nullable Integer passwordError
    ) {
        this.nomError = nomError;
        this.prenomError = prenomError;
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.isDataValid = false;
    }

    SignupState(boolean isDataValid) {
        this.nomError = null ;
        this.prenomError = null;
        this.usernameError = null;
        this.passwordError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    public Integer getNomError() {
        return nomError;
    }

    @Nullable
    public Integer getPrenomError() {
        return prenomError;
    }

    @Nullable
    Integer getUsernameError() {
        return usernameError;
    }

    @Nullable
    Integer getPasswordError() {
        return passwordError;
    }

    boolean isDataValid() {
        return isDataValid;
    }
}
