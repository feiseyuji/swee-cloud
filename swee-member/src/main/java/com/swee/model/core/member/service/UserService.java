/*
 * Generated by Cadeau Support.
 *
 * https://github.com/spldeolin/cadeau-support
 */

package com.swee.model.core.member.service;

import java.util.List;
import com.swee.model.core.api.CommonService;
import com.swee.model.core.api.dto.Page;
import com.swee.model.core.api.dto.PageParam;
import com.swee.model.core.member.model.User;

/**
 * “用户”业务
 *
 * @author Deolin 2018/5/28
 */
public interface UserService extends CommonService<User> {

    /**
     * 创建一个“用户” （附带业务校验）
     *
     * @param user 待创建“用户”
     * @return 自增ID
     */
    Long createEX(User user);

    /**
     * 获取一个“用户” （附带业务校验）
     *
     * @param id “用户”的ID
     * @return “用户”
     */
    User getEX(Long id);

    /**
     * 更新一个“用户” （附带业务校验）
     *
     * @param user 待更新“用户”
     */
    void updateEX(User user);

    /**
     * 删除一个“用户”
     *
     * @param id 待删除“用户”的ID
     */
    void deleteEX(Long id);

    /**
     * 删除多个资源 （附带业务校验，并返回详细情况）
     *
     * @param ids 待删除资源的ID列表
     * @return 删除情况
     */
    String deleteEX(List<Long> ids);

    /**
     * 分页获取资源
     *
     * @param pageParam 页码和每页条目数
     * @return Page 分页对象
     */
    Page<User> page(PageParam pageParam); // 根据具体需求拓展这个方法（追加搜索用参数等）



}