package com.accentrix.hku.web.configuration;

import java.util.ArrayList;
import java.util.List;

public class ScholarTierVo {

    private String tier;

    private boolean itemDisabled;

    public static List<ScholarTierVo> initTiersDropDownList() {
        List<ScholarTierVo> tiers = new ArrayList<ScholarTierVo>();
        for (int i = 1; i <= 6; i++) {
            ScholarTierVo tier = new ScholarTierVo();
            tier.setTier("Tier " + i);
            tiers.add(tier);
        }
        return tiers;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public boolean isItemDisabled() {
        return itemDisabled;
    }

    public void setItemDisabled(boolean itemDisabled) {
        this.itemDisabled = itemDisabled;
    }

}
