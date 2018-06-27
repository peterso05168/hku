package com.accentrix.hku.service.scholar;

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

import com.accentrix.hku.vo.scholar.ScholarDtlRequirementVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月19日 上午11:45:02 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "ScholarDtlRequirement Service", description = "scholarDtlRequirementService")
public interface ScholarDtlRequirementService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/scholarDtlRequirement/get/", verb = ApiVerb.GET, description = "get scholarDtlRequirement by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ScholarDtlRequirementVo get(
            @ApiQueryParam(name = "id", description = "The scholarDtlRequirement id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/scholarDtlRequirement/", verb = ApiVerb.POST, description = "save scholarDtlRequirement", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ScholarDtlRequirementVo save(@ApiBodyObject ScholarDtlRequirementVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/scholarDtlRequirement/save-list/", verb = ApiVerb.POST, description = "save scholarDtlRequirement list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<ScholarDtlRequirementVo> save(List<ScholarDtlRequirementVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/scholarDtlRequirement/{id}/", verb = ApiVerb.DELETE, description = "delete scholarDtlRequirement by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The scholarDtlRequirement Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/scholarDtlRequirement/delete-scholarDtlRequirement/", verb = ApiVerb.POST, description = "delete scholarDtlRequirement", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject ScholarDtlRequirementVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/scholarDtlRequirement/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ScholarDtlRequirementVo> findList();

    @POST
    @Path("/findByScholarDtlId/")
    @ApiMethod(path = "/api/rest/scholarDtlRequirement/findByScholarDtlId/", verb = ApiVerb.POST, description = "findByScholarDtlId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ScholarDtlRequirementVo> findByScholarDtlId(
            @ApiQueryParam(name = "scholarDtlId", description = "The scholarDtl id") @QueryParam("scholarDtlId") String scholarDtlId);
}
