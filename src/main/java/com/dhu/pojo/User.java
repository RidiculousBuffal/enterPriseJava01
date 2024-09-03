package com.dhu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;          // 用户ID
    private String username; // 用户名
    private String password; // 密码
}
