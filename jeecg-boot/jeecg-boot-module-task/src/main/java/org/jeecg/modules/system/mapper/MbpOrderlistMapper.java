package org.jeecg.modules.system.mapper;

import java.util.List;
import org.jeecg.modules.system.entity.MbpOrderlist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.system.vo.PlanVO;

/**
 * @Description: 订单明细
 * @Author: jeecg-boot
 * @Date:   2020-08-19
 * @Version: V1.0
 */
public interface MbpOrderlistMapper extends BaseMapper<MbpOrderlist> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<MbpOrderlist> selectByMainId(@Param("mainId") String mainId);

	/**
	 * 订单明细-通过主表ID查询，生成对应的生产计划明细
	 * @param id 订单编号
	 * @return PlanVO 订单明细对应的计划
	 */
	List<PlanVO> queryListToPlan(String id);
}
