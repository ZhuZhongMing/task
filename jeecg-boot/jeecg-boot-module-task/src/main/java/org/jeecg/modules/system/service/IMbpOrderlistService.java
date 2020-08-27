package org.jeecg.modules.system.service;

import org.jeecg.modules.system.entity.MbpOrderlist;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.system.vo.PlanVO;

import java.util.List;

/**
 * @Description: 订单明细
 * @Author: jeecg-boot
 * @Date:   2020-08-19
 * @Version: V1.0
 */
public interface IMbpOrderlistService extends IService<MbpOrderlist> {

	public List<MbpOrderlist> selectByMainId(String mainId);

    /**
     * 订单明细-通过主表ID查询，生成对应的生产计划明细
     * @param id 订单编号
     * @return PlanVO 订单明细对应的计划
     */
    List<PlanVO> queryListToPlan(String id);
}
