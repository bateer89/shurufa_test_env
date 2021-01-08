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
 * @Description: 汉字对照表
 * @Author: jeecg-boot
 * @Date:   2021-01-07
 * @Version: V1.0
 */
@Data
@TableName("zn_words")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zn_words对象", description="汉字对照表")
public class ZnWords implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**汉字*/
	@Excel(name = "汉字", width = 15)
    @ApiModelProperty(value = "汉字")
    private java.lang.String words;
	/**键盘序*/
	@Excel(name = "键盘序", width = 15)
    @ApiModelProperty(value = "键盘序")
    private java.lang.String keyboardSequence;
	/**键盘序*/
	@Excel(name = "键盘序", width = 15)
    @ApiModelProperty(value = "键盘序")
    private java.lang.String keyboard_simple_sequence1;
	/**键盘序*/
	@Excel(name = "键盘序", width = 15)
    @ApiModelProperty(value = "键盘序")
    private java.lang.String keyboard_simple_sequence2;
	/**键盘序*/
	@Excel(name = "键盘序", width = 15)
    @ApiModelProperty(value = "键盘序")
    private java.lang.String keyboard_simple_sequence3;
	/**全码笔画*/
	@Excel(name = "全码笔画", width = 15)
    @ApiModelProperty(value = "全码笔画")
    private java.lang.String all_strokes;
	/**简码笔画*/
	@Excel(name = "简码笔画", width = 15)
    @ApiModelProperty(value = "简码笔画")
    private java.lang.String simple_strokes1;
	/**简码笔画*/
	@Excel(name = "简码笔画", width = 15)
    @ApiModelProperty(value = "简码笔画")
    private java.lang.String simple_strokes2;
	/**简码笔画*/
	@Excel(name = "简码笔画", width = 15)
    @ApiModelProperty(value = "简码笔画")
    private java.lang.String simple_strokes3;
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
