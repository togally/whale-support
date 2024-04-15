package com.whale.cloud.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whale.cloud.dto.PageParam;
import com.whale.cloud.entity.BaseEntity;
import com.whale.cloud.facotry.ResultFactory;
import com.whale.cloud.result.BaseResult;
import com.whale.cloud.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.NotNull;

/**
 * @author jiazhiwei
 */
@Validated
public abstract class BaseController<T extends BaseEntity, S extends IService<T>>{
    @Autowired
    private S baseService;

    /**
     * 新增
     *
     * @param entity
     * @return
     */
    @PostMapping("add")
    public BaseResult<T> add(T entity) {
        BaseResult check = validate(entity, true);
        if (check.isNotSuccess()) {
            return check;
        }
        baseService.save(entity);
        return ResultFactory.success(entity);
    }

    /**
     * 校验
     *
     * @param entity
     * @param isSave true 保存操作 false 更新操作
     * @return
     */
    private BaseResult validate(T entity, boolean isSave) {
        return ResultFactory.success();
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @PostMapping("delete")
    public BaseResult<Boolean> delete(@NotNull(message = "id不能为null") Long id) {
        return ResultFactory.success(baseService.removeById(id));
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @PostMapping("updateById")
    public BaseResult<Boolean> updateById(T entity) {
        BaseResult check = validate(entity, true);
        if (check.isNotSuccess()) {
            return check;
        }
        return ResultFactory.success(baseService.updateById(entity));
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @PostMapping("findById")
    public BaseResult<T> findById(@NotNull(message = "id不能为null") Long id) {
        return ResultFactory.success(baseService.getById(id));
    }


    /**
     * 分页查询
     *
     * @param pageParam
     * @return
     */
    @PostMapping("page")
    public PageResult<T> page(PageParam pageParam) {
        return ResultFactory.success(baseService.page(pageParam));
    }
}
