package com.sipc.intelligentfarmbackend.pojo.model.res;

import com.sipc.intelligentfarmbackend.pojo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRes {
    private User user;
    private String token;
}
