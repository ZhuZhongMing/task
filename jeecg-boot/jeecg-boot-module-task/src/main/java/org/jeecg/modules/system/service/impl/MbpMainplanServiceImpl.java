package org.jeecg.modules.system.service.impl;

import org.jeecg.modules.system.entity.MbpMainplan;
import org.jeecg.modules.system.mapper.MbpMainplanMapper;
import org.jeecg.modules.system.service.IMbpMainplanService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 生产计划明细
 * @Author: jeecg-boot
 * @Date:   2020-08-25
 * @Version: V1.0
 */
@Service
public class MbpMainplanServiceImpl extends ServiceImpl<MbpMainplanMapper, MbpMainplan> implements IMbpMainplanService {
	
	@Autowired
	private MbpMainplanMapper mbpMainplanMapper;
	
	@Override
	public List<MbpMainplan> selectByMainId(String mainId) {
		return mbpMainplanMapper.selectByMainId(mainId);
	}
}
