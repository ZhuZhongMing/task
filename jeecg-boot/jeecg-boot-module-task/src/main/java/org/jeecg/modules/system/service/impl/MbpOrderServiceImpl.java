package org.jeecg.modules.system.service.impl;

import org.jeecg.modules.system.entity.MbpOrder;
import org.jeecg.modules.system.entity.MbpOrderlist;
import org.jeecg.modules.system.mapper.MbpOrderlistMapper;
import org.jeecg.modules.system.mapper.MbpOrderMapper;
import org.jeecg.modules.system.service.IMbpOrderService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 订单
 * @Author: jeecg-boot
 * @Date:   2020-08-19
 * @Version: V1.0
 */
@Service
public class MbpOrderServiceImpl extends ServiceImpl<MbpOrderMapper, MbpOrder> implements IMbpOrderService {

	@Autowired
	private MbpOrderMapper mbpOrderMapper;
	@Autowired
	private MbpOrderlistMapper mbpOrderlistMapper;
	
	@Override
	@Transactional
	public void saveMain(MbpOrder mbpOrder, List<MbpOrderlist> mbpOrderlistList) {
		mbpOrderMapper.insert(mbpOrder);
		if(mbpOrderlistList!=null && mbpOrderlistList.size()>0) {
			for(MbpOrderlist entity:mbpOrderlistList) {
				//外键设置
				entity.setOrderId(mbpOrder.getId());
				mbpOrderlistMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(MbpOrder mbpOrder,List<MbpOrderlist> mbpOrderlistList) {
		mbpOrderMapper.updateById(mbpOrder);
		
		//1.先删除子表数据
		mbpOrderlistMapper.deleteByMainId(mbpOrder.getId());
		
		//2.子表数据重新插入
		if(mbpOrderlistList!=null && mbpOrderlistList.size()>0) {
			for(MbpOrderlist entity:mbpOrderlistList) {
				//外键设置
				entity.setOrderId(mbpOrder.getId());
				mbpOrderlistMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		mbpOrderlistMapper.deleteByMainId(id);
		mbpOrderMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			mbpOrderlistMapper.deleteByMainId(id.toString());
			mbpOrderMapper.deleteById(id);
		}
	}
	
}
