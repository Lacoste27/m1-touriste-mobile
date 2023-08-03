package com.app.tourist.data.sources.api.users;

import com.app.tourist.core.utils.ApiResponse;
import com.app.tourist.core.utils.Result;
import com.app.tourist.data.models.UserModel;

public interface ApiUserSource {
    Result<ApiResponse> getAllUser();
    Result<ApiResponse> login(String username, String password);
}
