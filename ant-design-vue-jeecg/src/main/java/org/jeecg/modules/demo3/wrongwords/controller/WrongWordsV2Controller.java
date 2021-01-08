package org.jeecg.modules.demo3.wrongwords.controller;

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
import org.jeecg.modules.demo3.wrongwords.entity.WrongWordsV2;
import org.jeecg.modules.demo3.wrongwords.service.IWrongWordsV2Service;

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
 * @Description: wrong_words_v2
 * @Author: jeecg-boot
 * @Date:   2021-01-07
 * @Version: V1.0
 */
@Api(tags="wrong_words_v2")
@RestController
@RequestMapping("/wrongwords/wrongWordsV2")
@Slf4j
public class WrongWordsV2Controller extends JeecgController<WrongWordsV2, IWrongWordsV2Service> {
	@Autowired
	private IWrongWordsV2Service wrongWordsV2Service;
	
	/**
	 * 分页列表查询
	 *
	 * @param wrongWordsV2
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "wrong_words_v2-分页列表查询")
	@ApiOperation(value="wrong_words_v2-分页列表查询", notes="wrong_words_v2-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(WrongWordsV2 wrongWordsV2,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<WrongWordsV2> queryWrapper = QueryGenerator.initQueryWrapper(wrongWordsV2, req.getParameterMap());
		Page<WrongWordsV2> page = new Page<WrongWordsV2>(pageNo, pageSize);
		IPage<WrongWordsV2> pageList = wrongWordsV2Service.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param wrongWordsV2
	 * @return
	 */
	@AutoLog(value = "wrong_words_v2-添加")
	@ApiOperation(value="wrong_words_v2-添加", notes="wrong_words_v2-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody WrongWordsV2 wrongWordsV2) {
		wrongWordsV2Service.save(wrongWordsV2);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param wrongWordsV2
	 * @return
	 */
	@AutoLog(value = "wrong_words_v2-编辑")
	@ApiOperation(value="wrong_words_v2-编辑", notes="wrong_words_v2-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody WrongWordsV2 wrongWordsV2) {
		wrongWordsV2Service.updateById(wrongWordsV2);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "wrong_words_v2-通过id删除")
	@ApiOperation(value="wrong_words_v2-通过id删除", notes="wrong_words_v2-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		wrongWordsV2Service.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "wrong_words_v2-批量删除")
	@ApiOperation(value="wrong_words_v2-批量删除", notes="wrong_words_v2-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.wrongWordsV2Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "wrong_words_v2-通过id查询")
	@ApiOperation(value="wrong_words_v2-通过id查询", notes="wrong_words_v2-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		WrongWordsV2 wrongWordsV2 = wrongWordsV2Service.getById(id);
		if(wrongWordsV2==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(wrongWordsV2);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param wrongWordsV2
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, WrongWordsV2 wrongWordsV2) {
        return super.exportXls(request, wrongWordsV2, WrongWordsV2.class, "wrong_words_v2");
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
        return super.importExcel(request, response, WrongWordsV2.class);
    }

}
