package org.jeecg.modules.demo3.wrongwords.entity;

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
 * @Description: wrong_words
 * @Author: jeecg-boot
 * @Date:   2021-01-06
 * @Version: V1.0
 */
@Data
@TableName("wrong_words")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="wrong_words对象", description="wrong_words")
public class WrongWords implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
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
	/**用户id*/
	@Excel(name = "用户id", width = 15)
    @ApiModelProperty(value = "用户id")
    private java.lang.String userId;
	/**汉字id*/
	@Excel(name = "汉字id", width = 15)
    @ApiModelProperty(value = "汉字id")
    private java.lang.Integer wordsId;
	/**分组id*/
	@Excel(name = "分组id", width = 15)
    @ApiModelProperty(value = "分组id")
    private java.lang.Integer groupId;
	/**是否检查过*/
	@Excel(name = "是否检查过", width = 15)
    @ApiModelProperty(value = "是否检查过")
    private java.lang.Integer ifChecked;
	/**是否通过*/
	@Excel(name = "是否通过", width = 15)
    @ApiModelProperty(value = "是否通过")
    private java.lang.Integer ifPassed;
	/**成功的输入*/
	@Excel(name = "成功的输入", width = 15)
    @ApiModelProperty(value = "成功的输入")
    private java.lang.String input;
	/**反馈类型*/
	@Excel(name = "反馈类型", width = 15)
    @ApiModelProperty(value = "反馈类型")
    private java.lang.Integer feedbackType;
	/**反馈备注*/
	@Excel(name = "反馈备注", width = 15)
    @ApiModelProperty(value = "反馈备注")
    private java.lang.String feedbackRemark;
}
