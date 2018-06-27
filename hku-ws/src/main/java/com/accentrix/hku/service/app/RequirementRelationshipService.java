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

import com.accentrix.hku.vo.app.RequirementRelationshipVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:26:52
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "RequirementRelationship Service", description = "requirementRelationshipService")
public interface RequirementRelationshipService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/requirementRelationship/get/", verb = ApiVerb.GET, description = "get requirementRelationship by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    RequirementRelationshipVo get(
            @ApiQueryParam(name = "id", description = "The requirement id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/requirementRelationship/", verb = ApiVerb.POST, description = "save requirementRelationship", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    RequirementRelationshipVo save(@ApiBodyObject RequirementRelationshipVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/requirementRelationship/save-list/", verb = ApiVerb.POST, description = "save requirementRelationship list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<RequirementRelationshipVo> save(List<RequirementRelationshipVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/requirementRelationship/{id}/", verb = ApiVerb.DELETE, description = "delete requirementRelationship by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The requirement Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/requirementRelationship/delete-requirement/", verb = ApiVerb.POST, description = "delete requirementRelationship", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject RequirementRelationshipVo vo);

    @POST
    @Path("/findListByParentId/")
    @ApiMethod(path = "/api/rest/requirementRelationship/findListByParentId/", verb = ApiVerb.POST, description = "findListByParentId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<RequirementRelationshipVo> findListByParentId(
            @ApiQueryParam(name = "parentRequirementId", description = "The parentRequirementId") @QueryParam("parentRequirementId") String parentRequirementId);
}
