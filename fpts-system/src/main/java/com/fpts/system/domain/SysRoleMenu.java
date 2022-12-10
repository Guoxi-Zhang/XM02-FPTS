package com.fpts.system.domain;

import com.fpts.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 角色和菜单关联 sys_role_menu
 *
 * @author ruoyi
 */
public class SysRoleMenu {
    /**
     * 角色ID
     */
    @Excel(name = "角色ID", cellType = Excel.ColumnType.NUMERIC)
    private Long roleId;

    /**
     * 菜单ID
     */
    @Excel(name = "菜单ID", cellType = Excel.ColumnType.NUMERIC)
    private Long menuId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("roleId", getRoleId())
                .append("menuId", getMenuId())
                .toString();
    }
}
