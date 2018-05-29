package top.catalinali.form;

import lombok.Data;

/**
 * @Author: lx
 * @Description:
 * @Date: Created on 15:47 2018/5/29
 * @Modefied by:
 */
@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
}
