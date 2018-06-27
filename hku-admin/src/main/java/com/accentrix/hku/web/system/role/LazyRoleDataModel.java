package com.accentrix.hku.web.system.role;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import com.accentrix.hku.service.staff.RoleService;
import com.accentrix.hku.util.PaginationUtil;
import com.accentrix.hku.vo.staff.RoleForm;
import com.accentrix.hku.vo.staff.RoleVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月30日 上午10:31:06
 * @version 1.0
 */

@Configurable(preConstruction = true)
public class LazyRoleDataModel extends LazyDataModel<RoleVo> {

    private static final Logger LOG = LoggerFactory.getLogger(LazyRoleDataModel.class);

    private static final long serialVersionUID = 1L;

    @Autowired
    private RoleService roleService;
    private RoleVo roleVo;

    // 是否为第一页
    private boolean isFirst = true;

    public LazyRoleDataModel(RoleVo roleVo) {
        this.roleVo = roleVo;
    }

    @Override
    public RoleVo getRowData(String rowKey) {
        return roleService.get(rowKey);
    }

    @Override
    public Object getRowKey(RoleVo roleVo) {
        return roleVo.getId();
    }

    @Override
    public List<RoleVo> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {
        filters.forEach((k, v) -> LOG.debug("{}={}", k, v));

        PageRequest pageable = PaginationUtil.getPageable(isFirst ? 1 : first, pageSize,
                (null == sortOrder || sortOrder == SortOrder.ASCENDING) ? Direction.ASC : Direction.DESC,
                sortField == null ? "id" : sortField);
        isFirst = false;
        RoleForm roleForm = new RoleForm(roleVo, pageable);
        Page<RoleVo> page = roleService.findPage(roleForm);
        setRowCount((int) page.getTotalElements());
        return page.getContent();
    }

    public RoleVo getRoleVo() {
        return roleVo;
    }

    public void setRoleVo(RoleVo roleVo) {
        this.roleVo = roleVo;
    }
}
