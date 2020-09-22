package com.swee.model.core.api.mapper;

import org.apache.ibatis.annotations.DeleteProvider;
import com.swee.model.core.api.mapper.provider.PhysicallyDeleteMapperProvider;

public interface PhysicallyDeleteMapper<M> {

    /**
     * <pre>
     * ############
     * #          #
     * #  DANGER  #
     * #          #
     * ############
     * </pre>
     */
    @DeleteProvider(type = PhysicallyDeleteMapperProvider.class, method = "dynamicSQL")
    int physicallyDelete(Long id);

}