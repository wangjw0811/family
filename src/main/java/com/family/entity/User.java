package com.family.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *  账号信息
 * </p>
 *
 * @author test
 * @since 2019-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    private String id;

    /**
     * 人员id
     */
    private String peopleId;

    /**
     * 用户名
     */
    private String name;

    /**
     * 身份证号，通过MD5加密后保存
     */
    private String idNumber;

    /**
     * 账号
     */
    private String email;

    /**
     * 密码，通过MD5加密后保存
     */
    private String password;


}
