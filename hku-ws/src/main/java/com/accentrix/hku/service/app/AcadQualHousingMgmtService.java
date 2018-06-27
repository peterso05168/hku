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

import com.accentrix.hku.vo.app.AcadQualHousingMgmtVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年2月9日 下午3:12:46 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "AcadQualHousingMgmt Service", description = "acadQualHousingMgmtService")
public interface AcadQualHousingMgmtService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/acadQualHousingMgmt/get/", verb = ApiVerb.GET, description = "get acadQualHousingMgmt by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AcadQualHousingMgmtVo get(
            @ApiQueryParam(name = "id", description = "The acadQualHousingMgmt id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/acadQualHousingMgmt/", verb = ApiVerb.POST, description = "save acadQualHousingMgmt", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AcadQualHousingMgmtVo save(@ApiBodyObject AcadQualHousingMgmtVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/acadQualHousingMgmt/save-list/", verb = ApiVerb.POST, description = "save acadQualHousingMgmt list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<AcadQualHousingMgmtVo> save(List<AcadQualHousingMgmtVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/acadQualHousingMgmt/{id}/", verb = ApiVerb.DELETE, description = "delete acadQualHousingMgmt by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The acadQualHousingMgmt Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/acadQualHousingMgmt/delete-acadQualHousingMgmt/", verb = ApiVerb.POST, description = "delete acadQualHousingMgmt", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject AcadQualHousingMgmtVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/acadQualHousingMgmt/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AcadQualHousingMgmtVo> findList();
}
