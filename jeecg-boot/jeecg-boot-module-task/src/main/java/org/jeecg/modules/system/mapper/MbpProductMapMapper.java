package org.jeecg.modules.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.system.entity.MbpProductMap;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 产品表
 * @Author: jeecg-boot
 * @Date:   2020-08-18
 * @Version: V1.0
 */
public interface MbpProductMapMapper extends BaseMapper<MbpProductMap> {

    /**
     * 根据 ids查询，查询产品所需的物料名称（多个物料名逗号隔开）
     * @param ids - 产品编号
     * @return
     */
    List<MbpProductMap> getMaterialNamesByIds(List<String> ids);

}
