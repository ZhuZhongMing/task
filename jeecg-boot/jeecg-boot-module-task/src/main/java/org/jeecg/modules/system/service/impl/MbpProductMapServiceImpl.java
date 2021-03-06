package org.jeecg.modules.system.service.impl;

import org.jeecg.modules.system.entity.MbpProductMap;
import org.jeecg.modules.system.mapper.MbpProductMapMapper;
import org.jeecg.modules.system.service.IMbpProductMapService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 产品表
 * @Author: jeecg-boot
 * @Date:   2020-08-18
 * @Version: V1.0
 */
@Service
public class MbpProductMapServiceImpl extends ServiceImpl<MbpProductMapMapper, MbpProductMap> implements IMbpProductMapService {

    @Resource
    private MbpProductMapMapper mbpProductMapMapper;

    @Override
    public Map<String, String> getMaterialNamesByIds(List<String> ids) {
        List<MbpProductMap> list = this.baseMapper.getMaterialNamesByIds(ids);

        Map<String, String> res = new HashMap<String, String>();
        list.forEach(item -> {
                    if (res.get(item.getId()) == null) {
                        res.put(item.getId(), item.getMaterialName());
                    } else {
                        res.put(item.getId(), res.get(item.getId()) + "," + item.getMaterialName());
                    }
                }
        );
        return res;
    }
}
