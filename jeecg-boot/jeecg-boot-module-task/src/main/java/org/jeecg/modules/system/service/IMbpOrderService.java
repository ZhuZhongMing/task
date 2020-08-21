package org.jeecg.modules.system.service;

import org.jeecg.modules.system.entity.MbpOrderlist;
import org.jeecg.modules.system.entity.MbpOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 订单
 * @Author: jeecg-boot
 * @Date:   2020-08-19
 * @Version: V1.0
 */
public interface IMbpOrderService extends IService<MbpOrder> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(MbpOrder mbpOrder, List<MbpOrderlist> mbpOrderlistList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(MbpOrder mbpOrder, List<MbpOrderlist> mbpOrderlistList);
	
	/**
	 * 删除一对多
	 */
	public void delMain(String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);
	
}
