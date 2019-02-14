package com.family.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 族员信息表
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
     * 身份证号，通过MD5加密后保存
     */
    private String idNumber;

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
    @ApiModelProperty(example = "0 女 1 男")
    private Integer sex;

    /**
     * 生
     */
    private LocalDateTime birthDay;

    /**
     * 婚嫁
     */
    @ApiModelProperty(value = "是否婚嫁",example = "0 未婚 1 已婚")
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

    /**
     * 葬地
     */
    private String cemetery;

    /**
     * 说明
     */
    private String description;


}
