package com.qiu.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.io.InputStream;
import java.util.Date;

/**
 * 文件表
 *
 * @author 教研源码
 */
@Data
@TableName("inf_file")
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfFileDO {

    /**
     * 文件路径
     */
    @TableId(type = IdType.INPUT)
    private String id;
    /**
     * 文件类型
     *
     * 通过 {@link cn.hutool.core.io.FileTypeUtil#getType(InputStream)} 获取
     */
    @TableField(value = "`type`")
    private String type;
    /**
     * 文件内容
     */
    private byte[] content;


    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 最后更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 创建者，目前使用 SysUser 的 id 编号
     *
     * 使用 String 类型的原因是，未来可能会存在非数值的情况，留好拓展性。
     */
    @TableField(fill = FieldFill.INSERT)
    private String creator;
    /**
     * 更新者，目前使用 SysUser 的 id 编号
     *
     * 使用 String 类型的原因是，未来可能会存在非数值的情况，留好拓展性。
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updater;
    /**
     * 是否删除
     */
    @TableLogic
    private Boolean deleted;

    @TableField(exist = false)
    private String url;

}
