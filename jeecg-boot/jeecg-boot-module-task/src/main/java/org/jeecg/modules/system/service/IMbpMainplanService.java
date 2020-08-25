package org.jeecg.modules.system.service;

import org.jeecg.modules.system.entity.MbpMainplan;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 生产计划明细
 * @Author: jeecg-boot
 * @Date:   2020-08-25
 * @Version: V1.0
 */
public interface IMbpMainplanService extends IService<MbpMainplan> {

	public List<MbpMainplan> selectByMainId(String mainId);
}
