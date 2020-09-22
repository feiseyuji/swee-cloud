package com.swee.model.core.api.mapper;

import org.apache.ibatis.annotations.UpdateProvider;
import com.swee.model.core.api.mapper.provider.UpdateMapperProvider;

public interface UpdateMapper<M> {

    @UpdateProvider(type = UpdateMapperProvider.class, method = "dynamicSQL")
    int updateByIdSelective(M model);

}
