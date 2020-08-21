package org.jeecg.modules.system.service;

import org.jeecg.modules.system.entity.MbpOrderlist;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 订单明细
 * @Author: jeecg-boot
 * @Date:   2020-08-19
 * @Version: V1.0
 */
public interface IMbpOrderlistService extends IService<MbpOrderlist> {

	public List<MbpOrderlist> selectByMainId(String mainId);
}
