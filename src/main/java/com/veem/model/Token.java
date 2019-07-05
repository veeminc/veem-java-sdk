package com.veem.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.veem.constants.Scope;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Token
{
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("user_name")
    private String username;

    @JsonDeserialize(as = Long.class)
    @JsonProperty("account_id")
    private Long accountId;

    @JsonDeserialize(as = Long.class)
    @JsonProperty("expires_in")
    private Long expiresIn;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonDeserialize(using = Scope.Converter.class)
    private Scope scope;

    @JsonDeserialize(as = Long.class)
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("refresh_token")
    private String refreshToken;
}
