package com.accentrix.hku.web.management;

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

import com.accentrix.hku.service.applicant.ApplicantInformationService;
import com.accentrix.hku.util.PaginationUtil;
import com.accentrix.hku.vo.applicant.ApplicantInformationForm;
import com.accentrix.hku.vo.applicant.ApplicantInformationVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月30日 上午10:31:06
 * @version 1.0
 */

@Configurable(preConstruction = true)
public class LazyApplicantInformationDataModel extends LazyDataModel<ApplicantInformationVo> {

    private static final Logger LOG = LoggerFactory.getLogger(LazyApplicantInformationDataModel.class);

    private static final long serialVersionUID = 1L;

    @Autowired
    private ApplicantInformationService applicantInformationService;
    private ApplicantInformationVo applicantInformationVo;

    // 是否为第一页
    private boolean isFirst = true;

    public LazyApplicantInformationDataModel(ApplicantInformationVo applicantInformationVo) {
        this.applicantInformationVo = applicantInformationVo;
    }

    @Override
    public ApplicantInformationVo getRowData(String rowKey) {
        return applicantInformationService.get(rowKey);
    }

    @Override
    public Object getRowKey(ApplicantInformationVo applicantInformationVo) {
        return applicantInformationVo.getId();
    }

    @Override
    public List<ApplicantInformationVo> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {
        filters.forEach((k, v) -> LOG.debug("{}={}", k, v));

        PageRequest pageable = PaginationUtil.getPageable(isFirst ? 1 : first, pageSize,
                (null == sortOrder || sortOrder == SortOrder.ASCENDING) ? Direction.ASC : Direction.DESC,
                sortField == null ? "id" : sortField);
        isFirst = false;
        ApplicantInformationForm applicantInformationForm = new ApplicantInformationForm(applicantInformationVo,
                pageable);
        Page<ApplicantInformationVo> page = applicantInformationService.findPage(applicantInformationForm);
        setRowCount((int) page.getTotalElements());
        return page.getContent();
    }

    public ApplicantInformationVo getApplicantInformationVo() {
        return applicantInformationVo;
    }

    public void setApplicantInformationVo(ApplicantInformationVo applicantInformationVo) {
        this.applicantInformationVo = applicantInformationVo;
    }
}
