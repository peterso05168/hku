package com.accentrix.hku.service.campaign;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.jsondoc.core.pojo.ApiVerb;
import org.springframework.data.domain.Page;

import com.accentrix.hku.jaxb.PageAdapter;
import com.accentrix.hku.vo.campaign.CampaignForm;
import com.accentrix.hku.vo.campaign.CampaignVo;
import com.accentrix.hku.vo.campaign.CritChinaAndNjVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 下午2:29:13
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Campaign Service", description = "campaignService")
public interface CampaignService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/campaign/get/", verb = ApiVerb.GET, description = "get campaign by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    CampaignVo get(@ApiQueryParam(name = "id", description = "The campaign id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/campaign/", verb = ApiVerb.POST, description = "save campaign", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    CampaignVo save(@ApiBodyObject CampaignVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/campaign/save-list/", verb = ApiVerb.POST, description = "save campaign list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<CampaignVo> save(@ApiBodyObject List<CampaignVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/campaign/{id}/", verb = ApiVerb.DELETE, description = "delete campaign by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The campaign Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/campaign/delete-campaign/", verb = ApiVerb.POST, description = "delete campaign", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject CampaignVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/campaign/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<CampaignVo> findList();

    @POST
    @Path("/findPage/")
    @ApiMethod(path = "/api/rest/campaign/findPage/", verb = ApiVerb.POST, description = "findPage", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @XmlJavaTypeAdapter(PageAdapter.class)
    Page<CampaignVo> findPage(@ApiBodyObject CampaignForm campaignForm);

    @POST
    @Path("/findCritList/")
    @ApiMethod(path = "/api/rest/campaign/findCritList/", verb = ApiVerb.POST, description = "findCritList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<CritChinaAndNjVo> findCritList(@ApiQueryParam(name = "cpgnId") @QueryParam("cpgnId") String cpgnId);
}
