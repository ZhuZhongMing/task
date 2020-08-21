package org.jeecg.modules.system.mapper;

import java.util.List;
import org.jeecg.modules.system.entity.MbpOrderlist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 订单明细
 * @Author: jeecg-boot
 * @Date:   2020-08-19
 * @Version: V1.0
 */
public interface MbpOrderlistMapper extends BaseMapper<MbpOrderlist> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<MbpOrderlist> selectByMainId(@Param("mainId") String mainId);
}
