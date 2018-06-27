package com.accentrix.hku.web.system.user;

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

import com.accentrix.hku.service.staff.StaffInformationService;
import com.accentrix.hku.util.PaginationUtil;
import com.accentrix.hku.vo.staff.StaffInformationForm;
import com.accentrix.hku.vo.staff.StaffInformationVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月30日 上午10:31:06
 * @version 1.0
 */

@Configurable(preConstruction = true)
public class LazyUserDataModel extends LazyDataModel<StaffInformationVo> {

    private static final Logger LOG = LoggerFactory.getLogger(LazyUserDataModel.class);

    private static final long serialVersionUID = 1L;

    @Autowired
    private StaffInformationService staffInformationService;
    private StaffInformationVo staffInformationVo;

    // 是否为第一页
    private boolean isFirst = true;

    public LazyUserDataModel(StaffInformationVo staffInformationVo) {
        this.staffInformationVo = staffInformationVo;
    }

    @Override
    public StaffInformationVo getRowData(String rowKey) {
        return staffInformationService.get(rowKey);
    }

    @Override
    public Object getRowKey(StaffInformationVo staffInformationVo) {
        return staffInformationVo.getId();
    }

    @Override
    public List<StaffInformationVo> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {
        filters.forEach((k, v) -> LOG.debug("{}={}", k, v));

        PageRequest pageable = PaginationUtil.getPageable(isFirst ? 1 : first, pageSize,
                (null == sortOrder || sortOrder == SortOrder.ASCENDING) ? Direction.ASC : Direction.DESC,
                sortField == null ? "id" : sortField);
        isFirst = false;
        StaffInformationForm staffInformationForm = new StaffInformationForm(staffInformationVo, pageable);
        Page<StaffInformationVo> page = staffInformationService.findPage(staffInformationForm);
        setRowCount((int) page.getTotalElements());
        return page.getContent();
    }

    public StaffInformationVo getStaffInformationVo() {
        return staffInformationVo;
    }

    public void setStaffInformationVo(StaffInformationVo staffInformationVo) {
        this.staffInformationVo = staffInformationVo;
    }
}
