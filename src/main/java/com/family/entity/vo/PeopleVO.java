package com.family.entity.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PeopleVO{
    /**
     * 代数
     */
    private Integer generation;
    /**
     * 代数说明
     */
    private String generationDesc;
}
