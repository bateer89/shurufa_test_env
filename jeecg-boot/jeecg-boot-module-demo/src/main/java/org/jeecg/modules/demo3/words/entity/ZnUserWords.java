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
 * @Description: 用户汉字完成表
 * @Author: jeecg-boot
 * @Date:   2021-01-06
 * @Version: V1.0
 */
@Data
@TableName("zn_user_words")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zn_user_words对象", description="用户汉字完成表")
public class ZnUserWords implements Serializable {
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
