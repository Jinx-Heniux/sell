package top.catalinali.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.catalinali.dataobject.ProductCategory;

import java.util.List;

/**
 * <pre>
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/15
 * </pre>
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer>{
    /**
     * 根据分类ids查询
     * @param typeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> typeList);
}
