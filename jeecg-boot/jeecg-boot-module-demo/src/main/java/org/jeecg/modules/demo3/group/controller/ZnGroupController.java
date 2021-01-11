package org.jeecg.modules.demo3.group.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo3.group.entity.ZnGroup;
import org.jeecg.modules.demo3.group.service.IZnGroupService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: zn_group
 * @Author: jeecg-boot
 * @Date:   2021-01-08
 * @Version: V1.0
 */
@Api(tags="zn_group")
@RestController
@RequestMapping("/group/znGroup")
@Slf4j
public class ZnGroupController extends JeecgController<ZnGroup, IZnGroupService> {
	@Autowired
	private IZnGroupService znGroupService;
	
	/**
	 * 分页列表查询
	 *
	 * @param znGroup
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "zn_group-分页列表查询")
	@ApiOperation(value="zn_group-分页列表查询", notes="zn_group-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZnGroup znGroup,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ZnGroup> queryWrapper = QueryGenerator.initQueryWrapper(znGroup, req.getParameterMap());
		Page<ZnGroup> page = new Page<ZnGroup>(pageNo, pageSize);
		IPage<ZnGroup> pageList = znGroupService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param znGroup
	 * @return
	 */
	@AutoLog(value = "zn_group-添加")
	@ApiOperation(value="zn_group-添加", notes="zn_group-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZnGroup znGroup) {
		znGroupService.save(znGroup);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param znGroup
	 * @return
	 */
	@AutoLog(value = "zn_group-编辑")
	@ApiOperation(value="zn_group-编辑", notes="zn_group-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZnGroup znGroup) {
		znGroupService.updateById(znGroup);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "zn_group-通过id删除")
	@ApiOperation(value="zn_group-通过id删除", notes="zn_group-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		znGroupService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "zn_group-批量删除")
	@ApiOperation(value="zn_group-批量删除", notes="zn_group-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.znGroupService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "zn_group-通过id查询")
	@ApiOperation(value="zn_group-通过id查询", notes="zn_group-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZnGroup znGroup = znGroupService.getById(id);
		if(znGroup==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(znGroup);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param znGroup
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZnGroup znGroup) {
        return super.exportXls(request, znGroup, ZnGroup.class, "zn_group");
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
        return super.importExcel(request, response, ZnGroup.class);
    }

}
