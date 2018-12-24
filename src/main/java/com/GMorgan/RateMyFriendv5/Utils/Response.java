package com.GMorgan.RateMyFriendv5.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {

    boolean isSuccessful;
    String message;
    // Or created another class AuthorizedResponse
    // TODO add user Role
    // TODO add timer period of Authentication
}
