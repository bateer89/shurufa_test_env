package org.jeecg.modules.demo3.words.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 汉字表
 * @Author: jeecg-boot
 * @Date:   2021-01-08
 * @Version: V1.0
 */
@Data
@TableName("zn_words")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zn_words对象", description="汉字表")
public class ZnWords implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.Integer id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**汉字*/
	@Excel(name = "汉字", width = 15)
    @ApiModelProperty(value = "汉字")
    private java.lang.String words;
	/**全码*/
	@Excel(name = "全码", width = 15)
    @ApiModelProperty(value = "全码")
    private java.lang.String keyboardSequence;
	/**简码1*/
	@Excel(name = "简码1", width = 15)
    @ApiModelProperty(value = "简码1")
    private java.lang.String keyboardSimpleSequence1;
	/**简码2*/
	@Excel(name = "简码2", width = 15)
    @ApiModelProperty(value = "简码2")
    private java.lang.String keyboardSimpleSequence2;
	/**简码3*/
	@Excel(name = "简码3", width = 15)
    @ApiModelProperty(value = "简码3")
    private java.lang.String keyboardSimpleSequence3;
	/**全码笔画*/
	@Excel(name = "全码笔画", width = 15)
    @ApiModelProperty(value = "全码笔画")
    private java.lang.String allStrokes;
	/**简码笔画1*/
	@Excel(name = "简码笔画1", width = 15)
    @ApiModelProperty(value = "简码笔画1")
    private java.lang.String simpleStrokes1;
	/**简码笔画2*/
	@Excel(name = "简码笔画2", width = 15)
    @ApiModelProperty(value = "简码笔画2")
    private java.lang.String simpleStrokes2;
	/**简码笔画3*/
	@Excel(name = "简码笔画3", width = 15)
    @ApiModelProperty(value = "简码笔画3")
    private java.lang.String simpleStrokes3;
	/**code1*/
	@Excel(name = "code1", width = 15)
    @ApiModelProperty(value = "code1")
    private java.lang.String code1;
	/**code2*/
	@Excel(name = "code2", width = 15)
    @ApiModelProperty(value = "code2")
    private java.lang.String code2;
	/**code3*/
	@Excel(name = "code3", width = 15)
    @ApiModelProperty(value = "code3")
    private java.lang.String code3;
	/**是否正确0:no;1:yes*/
	@Excel(name = "是否正确0:no;1:yes", width = 15)
    @ApiModelProperty(value = "是否正确0:no;1:yes")
    private java.lang.Integer ifAllPassed;
	/**是否检查过*/
	@Excel(name = "是否检查过", width = 15)
    @ApiModelProperty(value = "是否检查过")
    private java.lang.Integer ifChecked;
	/**检查者id*/
	@Excel(name = "检查者id", width = 15)
    @ApiModelProperty(value = "检查者id")
    private java.lang.String checkerid;
	/**检查者name*/
	@Excel(name = "检查者name", width = 15)
    @ApiModelProperty(value = "检查者name")
    private java.lang.String checkername;
}
