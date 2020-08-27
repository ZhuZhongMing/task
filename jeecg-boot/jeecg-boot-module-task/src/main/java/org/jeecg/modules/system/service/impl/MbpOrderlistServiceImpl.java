package org.jeecg.modules.system.service.impl;

import org.jeecg.modules.system.entity.MbpOrderlist;
import org.jeecg.modules.system.mapper.MbpOrderlistMapper;
import org.jeecg.modules.system.service.IMbpOrderlistService;
import org.jeecg.modules.system.vo.PlanVO;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @Description: 订单明细
 * @Author: jeecg-boot
 * @Date:   2020-08-19
 * @Version: V1.0
 */
@Service
public class MbpOrderlistServiceImpl extends ServiceImpl<MbpOrderlistMapper, MbpOrderlist> implements IMbpOrderlistService {
	
	@Resource
	private MbpOrderlistMapper mbpOrderlistMapper;
	
	@Override
	public List<MbpOrderlist> selectByMainId(String mainId) {
		return mbpOrderlistMapper.selectByMainId(mainId);
	}

	@Override
	public List<PlanVO> queryListToPlan(String id) {
		return mbpOrderlistMapper.queryListToPlan(id);
	}
}
