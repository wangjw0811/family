package com.family.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author test
 * @since 2019-02-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class People implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 父级id
     */
    private String parentId;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 姓氏
     */
    private String surname;

    /**
     * 名
     */
    private String forename;

    /**
     * 字
     */
    private String styleName;

    /**
     * 号
     */
    private String pseudonym;

    /**
     * 谥号
     */
    private String posthumousTitle;

    /**
     * 别号
     */
    private String alias;

    /**
     * 辈分
     */
    private String seniority;

    /**
     * 代数
     */
    private Integer generation;

    /**
     * 谱名
     */
    private String genealogyName;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 生
     */
    private LocalDateTime birthDay;

    /**
     * 婚嫁
     */
    private Integer ismarriage;

    /**
     * 配偶
     */
    private String spouseName;

    /**
     * 配偶户籍
     */
    private String spouseResidence;

    /**
     * 学历
     */
    private Integer educationalBackground;

    /**
     * 高校
     */
    private String collegesAndUniversities;

    /**
     * 电话
     */
    private String phoneNumber;

    /**
     * 其他联系方式
     */
    private String contactWay;

    /**
     * 职业
     */
    private Integer occupation;

    /**
     * 账号
     */
    private String accountNumber;

    /**
     * 密码
     */
    private String password;

    /**
     * 图像
     */
    private String image;

    /**
     * 卒
     */
    private LocalDateTime deathDay;

    private String cemetery;

    /**
     * 说明
     */
    private String description;


}
