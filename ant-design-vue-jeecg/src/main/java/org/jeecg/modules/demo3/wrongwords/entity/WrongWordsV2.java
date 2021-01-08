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
 * @Description: wrong_words_v2
 * @Author: jeecg-boot
 * @Date:   2021-01-07
 * @Version: V1.0
 */
@Data
@TableName("wrong_words_v2")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="wrong_words_v2对象", description="wrong_words_v2")
public class WrongWordsV2 implements Serializable {
    private static final long serialVersionUID = 1L;

	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**登录账号*/
	@Excel(name = "登录账号", width = 15)
    @ApiModelProperty(value = "登录账号")
    private java.lang.String username;
	/**汉字*/
	@Excel(name = "汉字", width = 15)
    @ApiModelProperty(value = "汉字")
    private java.lang.String words;
	/**汉字id*/
	@Excel(name = "汉字id", width = 15)
    @ApiModelProperty(value = "汉字id")
    private java.lang.Integer wordsId;
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
	/**是否通过*/
	@Excel(name = "是否通过", width = 15)
    @ApiModelProperty(value = "是否通过")
    private java.lang.Integer ifPassed;
	/**用户id*/
	@Excel(name = "用户id", width = 15)
    @ApiModelProperty(value = "用户id")
    private java.lang.String userId;
	/**是否检查过*/
	@Excel(name = "是否检查过", width = 15)
    @ApiModelProperty(value = "是否检查过")
    private java.lang.Integer ifChecked;
}
