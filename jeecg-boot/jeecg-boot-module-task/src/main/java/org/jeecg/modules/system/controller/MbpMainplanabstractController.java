package org.jeecg.modules.system.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.system.entity.MbpMainplan;
import org.jeecg.modules.system.entity.MbpMainplanabstract;
import org.jeecg.modules.system.vo.MbpMainplanabstractPage;
import org.jeecg.modules.system.service.IMbpMainplanabstractService;
import org.jeecg.modules.system.service.IMbpMainplanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 生产计划
 * @Author: jeecg-boot
 * @Date:   2020-08-25
 * @Version: V1.0
 */
@Api(tags="生产计划")
@RestController
@RequestMapping("/system/mbpMainplanabstract")
@Slf4j
public class MbpMainplanabstractController {
	@Autowired
	private IMbpMainplanabstractService mbpMainplanabstractService;
	@Autowired
	private IMbpMainplanService mbpMainplanService;
	
	/**
	 * 分页列表查询
	 *
	 * @param mbpMainplanabstract
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "生产计划-分页列表查询")
	@ApiOperation(value="生产计划-分页列表查询", notes="生产计划-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MbpMainplanabstract mbpMainplanabstract,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MbpMainplanabstract> queryWrapper = QueryGenerator.initQueryWrapper(mbpMainplanabstract, req.getParameterMap());
		Page<MbpMainplanabstract> page = new Page<MbpMainplanabstract>(pageNo, pageSize);
		IPage<MbpMainplanabstract> pageList = mbpMainplanabstractService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param mbpMainplanabstractPage
	 * @return
	 */
	@AutoLog(value = "生产计划-添加")
	@ApiOperation(value="生产计划-添加", notes="生产计划-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MbpMainplanabstractPage mbpMainplanabstractPage) {
		MbpMainplanabstract mbpMainplanabstract = new MbpMainplanabstract();
		BeanUtils.copyProperties(mbpMainplanabstractPage, mbpMainplanabstract);
		mbpMainplanabstractService.saveMain(mbpMainplanabstract, mbpMainplanabstractPage.getMbpMainplanList());
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mbpMainplanabstractPage
	 * @return
	 */
	@AutoLog(value = "生产计划-编辑")
	@ApiOperation(value="生产计划-编辑", notes="生产计划-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MbpMainplanabstractPage mbpMainplanabstractPage) {
		MbpMainplanabstract mbpMainplanabstract = new MbpMainplanabstract();
		BeanUtils.copyProperties(mbpMainplanabstractPage, mbpMainplanabstract);
		MbpMainplanabstract mbpMainplanabstractEntity = mbpMainplanabstractService.getById(mbpMainplanabstract.getId());
		if(mbpMainplanabstractEntity==null) {
			return Result.error("未找到对应数据");
		}
		mbpMainplanabstractService.updateMain(mbpMainplanabstract, mbpMainplanabstractPage.getMbpMainplanList());
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "生产计划-通过id删除")
	@ApiOperation(value="生产计划-通过id删除", notes="生产计划-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		mbpMainplanabstractService.delMain(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "生产计划-批量删除")
	@ApiOperation(value="生产计划-批量删除", notes="生产计划-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.mbpMainplanabstractService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "生产计划-通过id查询")
	@ApiOperation(value="生产计划-通过id查询", notes="生产计划-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MbpMainplanabstract mbpMainplanabstract = mbpMainplanabstractService.getById(id);
		if(mbpMainplanabstract==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(mbpMainplanabstract);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "生产计划明细-通过主表ID查询")
	@ApiOperation(value="生产计划明细-通过主表ID查询", notes="生产计划明细-通过主表ID查询")
	@GetMapping(value = "/queryMbpMainplanByMainId")
	public Result<?> queryMbpMainplanListByMainId(@RequestParam(name="id",required=true) String id) {
		List<MbpMainplan> mbpMainplanList = mbpMainplanService.selectByMainId(id);
		IPage <MbpMainplan> page = new Page<>();
		page.setRecords(mbpMainplanList);
		page.setTotal(mbpMainplanList.size());
		return Result.ok(page);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mbpMainplanabstract
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MbpMainplanabstract mbpMainplanabstract) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<MbpMainplanabstract> queryWrapper = QueryGenerator.initQueryWrapper(mbpMainplanabstract, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<MbpMainplanabstract> queryList = mbpMainplanabstractService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<MbpMainplanabstract> mbpMainplanabstractList = new ArrayList<MbpMainplanabstract>();
      if(oConvertUtils.isEmpty(selections)) {
          mbpMainplanabstractList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          mbpMainplanabstractList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<MbpMainplanabstractPage> pageList = new ArrayList<MbpMainplanabstractPage>();
      for (MbpMainplanabstract main : mbpMainplanabstractList) {
          MbpMainplanabstractPage vo = new MbpMainplanabstractPage();
          BeanUtils.copyProperties(main, vo);
          List<MbpMainplan> mbpMainplanList = mbpMainplanService.selectByMainId(main.getId());
          vo.setMbpMainplanList(mbpMainplanList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "生产计划列表");
      mv.addObject(NormalExcelConstants.CLASS, MbpMainplanabstractPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("生产计划数据", "导出人:"+sysUser.getRealname(), "生产计划"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
    }

    /**
    * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<MbpMainplanabstractPage> list = ExcelImportUtil.importExcel(file.getInputStream(), MbpMainplanabstractPage.class, params);
              for (MbpMainplanabstractPage page : list) {
                  MbpMainplanabstract po = new MbpMainplanabstract();
                  BeanUtils.copyProperties(page, po);
                  mbpMainplanabstractService.saveMain(po, page.getMbpMainplanList());
              }
              return Result.ok("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("文件导入失败！");
    }

}
