package com.corn.springboot.start.system.permission.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author suyiming3333
 * @since 2021-01-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysPermission extends Model<SysPermission> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 父类ID
     */
    private Integer parentId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
