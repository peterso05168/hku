package com.accentrix.hku.service.app;

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

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.jsondoc.core.pojo.ApiVerb;

import com.accentrix.hku.vo.app.AcadQualOthersVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年2月9日 下午3:13:06 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "AcadQualOthers Service", description = "acadQualOthersService")
public interface AcadQualOthersService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/acadQualOthers/get/", verb = ApiVerb.GET, description = "get acadQualOthers by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AcadQualOthersVo get(
            @ApiQueryParam(name = "id", description = "The acadQualOthers id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/acadQualOthers/", verb = ApiVerb.POST, description = "save acadQualOthers", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AcadQualOthersVo save(@ApiBodyObject AcadQualOthersVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/acadQualOthers/save-list/", verb = ApiVerb.POST, description = "save acadQualOthers list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<AcadQualOthersVo> save(List<AcadQualOthersVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/acadQualOthers/{id}/", verb = ApiVerb.DELETE, description = "delete acadQualOthers by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The acadQualOthers Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/acadQualOthers/delete-acadQualOthers/", verb = ApiVerb.POST, description = "delete acadQualOthers", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject AcadQualOthersVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/acadQualOthers/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AcadQualOthersVo> findList();

    @POST
    @Path("/getByAcadQualHousingMgmtId/")
    @ApiMethod(path = "/api/rest/acadQualOthers/getByAcadQualHousingMgmtId/", verb = ApiVerb.POST, description = "getByAcadQualHousingMgmtId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AcadQualOthersVo> getByAcadQualHousingMgmtId(
            @ApiQueryParam(name = "acadQualHousingMgmtId") @QueryParam("acadQualHousingMgmtId") String acadQualHousingMgmtId);

}
