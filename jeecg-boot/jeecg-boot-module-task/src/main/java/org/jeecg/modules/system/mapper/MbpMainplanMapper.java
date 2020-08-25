package org.jeecg.modules.system.mapper;

import java.util.List;
import org.jeecg.modules.system.entity.MbpMainplan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 生产计划明细
 * @Author: jeecg-boot
 * @Date:   2020-08-25
 * @Version: V1.0
 */
public interface MbpMainplanMapper extends BaseMapper<MbpMainplan> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<MbpMainplan> selectByMainId(@Param("mainId") String mainId);
}
