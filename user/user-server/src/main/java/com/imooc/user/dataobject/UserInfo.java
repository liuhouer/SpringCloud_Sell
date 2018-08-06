package com.imooc.user.dataobject;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author hellozjf
 */
@Entity
@Data
public class UserInfo {
    @Id
    private String id;
    private String username;
    private String password;
    private String openid;
    private Integer role;
}
