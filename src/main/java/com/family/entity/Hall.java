package com.family.entity;

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
public class Hall implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 始祖id
     */
    private String peopleId;

    /**
     * 姓氏
     */
    private String surname;

    /**
     * 堂号
     */
    private String hallName;

    /**
     * 堂联
     */
    private String hallCouplets;


}
