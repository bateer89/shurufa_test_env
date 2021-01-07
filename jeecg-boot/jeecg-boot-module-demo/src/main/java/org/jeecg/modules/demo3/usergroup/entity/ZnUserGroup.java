package org.jeecg.modules.demo3.usergroup.entity;

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
 * @Description: 用户任务表
 * @Author: jeecg-boot
 * @Date:   2021-01-07
 * @Version: V1.0
 */
@Data
@TableName("zn_user_group")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zn_user_group对象", description="用户任务表")
public class ZnUserGroup implements Serializable {
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
	@Excel(name = "用户id", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "id")
	@Dict(dictTable = "sys_user", dicText = "realname", dicCode = "id")
    @ApiModelProperty(value = "用户id")
    private java.lang.String userId;
	/**用户账号*/
	@Excel(name = "用户账号", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "username")
	@Dict(dictTable = "sys_user", dicText = "realname", dicCode = "username")
    @ApiModelProperty(value = "用户账号")
    private java.lang.String userCode;
	/**用户姓名*/
	@Excel(name = "用户姓名", width = 15)
    @ApiModelProperty(value = "用户姓名")
    private java.lang.String userName;
	/**任务分组id*/
	@Excel(name = "任务分组id", width = 15, dictTable = "zn_group_name", dicText = "group_name", dicCode = "group_id")
	@Dict(dictTable = "zn_group_name", dicText = "group_name", dicCode = "group_id")
    @ApiModelProperty(value = "任务分组id")
    private java.lang.Integer groupId;
	/**任务分组名称*/
	@Excel(name = "任务分组名称", width = 15)
    @ApiModelProperty(value = "任务分组名称")
    private java.lang.String groupName;
}
