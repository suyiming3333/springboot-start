package com.corn.springboot.start.system.permission.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色权限表
 * </p>
 *
 * @author suyiming3333
 * @since 2021-01-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRolePermission extends Model<SysRolePermission> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 权限ID
     */
    private Integer permissionId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
