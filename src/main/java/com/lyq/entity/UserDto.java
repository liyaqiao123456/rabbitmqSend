package com.lyq.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 统一用户信息
 *
 * @author yangbin
 * @date 2019年11月02日
 * <p>
 */
@Data
@Builder
public class UserDto {
    private Integer id;
    //姓名
    private String name;
    //女
    private Integer sex;
    //邮箱
    private String email;
    //手机号
    private String phone;
    //岗位
    private String post;
    //）
    private Integer type;
    //状态
    private Boolean enable;
    //上次密码更新时间
    private LocalDateTime updatePwdTime;
}
