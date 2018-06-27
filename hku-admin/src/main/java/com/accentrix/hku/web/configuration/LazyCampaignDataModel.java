package com.accentrix.hku.web.configuration;

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

import com.accentrix.hku.service.campaign.CampaignService;
import com.accentrix.hku.util.PaginationUtil;
import com.accentrix.hku.vo.campaign.CampaignForm;
import com.accentrix.hku.vo.campaign.CampaignVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月30日 上午10:31:06
 * @version 1.0
 */

@Configurable(preConstruction = true)
public class LazyCampaignDataModel extends LazyDataModel<CampaignVo> {

    private static final Logger LOG = LoggerFactory.getLogger(LazyCampaignDataModel.class);

    private static final long serialVersionUID = 1L;

    @Autowired
    private CampaignService campaignService;
    private CampaignVo campaignVo;

    // 是否为第一页
    private boolean isFirst = true;

    public LazyCampaignDataModel(CampaignVo campaignVo) {
        this.campaignVo = campaignVo;
    }

    @Override
    public CampaignVo getRowData(String rowKey) {
        return campaignService.get(rowKey);
    }

    @Override
    public Object getRowKey(CampaignVo campaignVo) {
        return campaignVo.getId();
    }

    @Override
    public List<CampaignVo> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {
        filters.forEach((k, v) -> LOG.debug("{}={}", k, v));

        PageRequest pageable = PaginationUtil.getPageable(isFirst ? 1 : first, pageSize,
                (null == sortOrder || sortOrder == SortOrder.ASCENDING) ? Direction.ASC : Direction.DESC,
                sortField == null ? "id" : sortField);
        isFirst = false;
        CampaignForm campaignForm = new CampaignForm(pageable, campaignVo);
        Page<CampaignVo> page = campaignService.findPage(campaignForm);
        setRowCount((int) page.getTotalElements());
        return page.getContent();
    }

    public CampaignVo getCampaignVo() {
        return campaignVo;
    }

    public void setCampaignVo(CampaignVo campaignVo) {
        this.campaignVo = campaignVo;
    }
}
