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

import com.accentrix.hku.service.app.ProgrammeChoiceService;
import com.accentrix.hku.util.PaginationUtil;
import com.accentrix.hku.vo.app.ProgrammeChoiceForm;
import com.accentrix.hku.vo.app.ProgrammeChoiceVo;

/** 
* @author 作者lance.mao  
* @Email lance.mao@accentrix.com 
* @date 创建时间：2018年6月6日 下午2:19:56 
*/
@Configurable(preConstruction = true)
public class LazyOfferProcessDataModel extends LazyDataModel<ProgrammeChoiceVo> {
    private static final Logger LOG = LoggerFactory.getLogger(LazyShortlistingDataModel.class);

    private static final long serialVersionUID = 1L;

    @Autowired
    private ProgrammeChoiceService programmeChoiceService;
    private ProgrammeChoiceVo programmeChoiceVo;

    // 是否为第一页
    private boolean isFirst = true;

    public LazyOfferProcessDataModel(ProgrammeChoiceVo programmeChoiceVo) {
        this.programmeChoiceVo = programmeChoiceVo;
    }

    @Override
    public ProgrammeChoiceVo getRowData(String rowKey) {
        return programmeChoiceService.get(rowKey);
    }

    @Override
    public Object getRowKey(ProgrammeChoiceVo programmeChoiceVo) {
        return programmeChoiceVo.getId();
    }

    @Override
    public List<ProgrammeChoiceVo> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {
        filters.forEach((k, v) -> LOG.debug("{}={}", k, v));

        PageRequest pageable = PaginationUtil.getPageable(isFirst ? 1 : first, pageSize,
                (null == sortOrder || sortOrder == SortOrder.ASCENDING) ? Direction.ASC : Direction.DESC,
                sortField == null ? "id" : sortField);
        isFirst = false;
        ProgrammeChoiceForm programmeChoiceForm = new ProgrammeChoiceForm(programmeChoiceVo, pageable);
        Page<ProgrammeChoiceVo> page = programmeChoiceService.findPage(programmeChoiceForm);
        setRowCount((int) page.getTotalElements());
        return page.getContent();
    }

    public ProgrammeChoiceVo getProgrammeChoiceVo() {
        return programmeChoiceVo;
    }

    public void setProgrammeChoiceVo(ProgrammeChoiceVo programmeChoiceVo) {
        this.programmeChoiceVo = programmeChoiceVo;
    }
}
