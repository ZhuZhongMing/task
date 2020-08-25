package org.jeecg.modules.system.service.impl;

import org.jeecg.modules.system.entity.MbpMainplanabstract;
import org.jeecg.modules.system.entity.MbpMainplan;
import org.jeecg.modules.system.mapper.MbpMainplanMapper;
import org.jeecg.modules.system.mapper.MbpMainplanabstractMapper;
import org.jeecg.modules.system.service.IMbpMainplanabstractService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 生产计划
 * @Author: jeecg-boot
 * @Date:   2020-08-25
 * @Version: V1.0
 */
@Service
public class MbpMainplanabstractServiceImpl extends ServiceImpl<MbpMainplanabstractMapper, MbpMainplanabstract> implements IMbpMainplanabstractService {

	@Autowired
	private MbpMainplanabstractMapper mbpMainplanabstractMapper;
	@Autowired
	private MbpMainplanMapper mbpMainplanMapper;
	
	@Override
	@Transactional
	public void saveMain(MbpMainplanabstract mbpMainplanabstract, List<MbpMainplan> mbpMainplanList) {
		mbpMainplanabstractMapper.insert(mbpMainplanabstract);
		if(mbpMainplanList!=null && mbpMainplanList.size()>0) {
			for(MbpMainplan entity:mbpMainplanList) {
				//外键设置
				entity.setMainplanAbstractId(mbpMainplanabstract.getId());
				mbpMainplanMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(MbpMainplanabstract mbpMainplanabstract,List<MbpMainplan> mbpMainplanList) {
		mbpMainplanabstractMapper.updateById(mbpMainplanabstract);
		
		//1.先删除子表数据
		mbpMainplanMapper.deleteByMainId(mbpMainplanabstract.getId());
		
		//2.子表数据重新插入
		if(mbpMainplanList!=null && mbpMainplanList.size()>0) {
			for(MbpMainplan entity:mbpMainplanList) {
				//外键设置
				entity.setMainplanAbstractId(mbpMainplanabstract.getId());
				mbpMainplanMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		mbpMainplanMapper.deleteByMainId(id);
		mbpMainplanabstractMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			mbpMainplanMapper.deleteByMainId(id.toString());
			mbpMainplanabstractMapper.deleteById(id);
		}
	}
	
}
