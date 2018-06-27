package com.accentrix.hku.portlet;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.faces.GenericFacesPortlet;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

public class PopupPortlet extends GenericFacesPortlet {

    @Override
    public void render(RenderRequest request, RenderResponse response) throws PortletException, IOException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        themeDisplay.setStatePopUp(true);

        super.render(request, response);
    }
}
