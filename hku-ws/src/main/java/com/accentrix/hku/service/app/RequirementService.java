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

import com.accentrix.hku.vo.app.RequirementVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午2:26:52 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Requirement Service", description = "requirementService")
public interface RequirementService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/requirement/get/", verb = ApiVerb.GET, description = "get requirement by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    RequirementVo get(@ApiQueryParam(name = "id", description = "The requirement id") @QueryParam("id") String id);

    @POST
    @Path("/getByFormProgId/")
    @ApiMethod(path = "/api/rest/requirement/getByFormProgId/", verb = ApiVerb.POST, description = "get by programme id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<String> getIdsByFormProgId(@ApiQueryParam(name = "formProgId") @QueryParam("formProgId") String formProgId);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/requirement/", verb = ApiVerb.POST, description = "save requirement", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    RequirementVo save(@ApiBodyObject RequirementVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/requirement/save-list/", verb = ApiVerb.POST, description = "save requirement list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<RequirementVo> save(List<RequirementVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/requirement/{id}/", verb = ApiVerb.DELETE, description = "delete requirement by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The requirement Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/requirement/delete-requirement/", verb = ApiVerb.POST, description = "delete requirement", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject RequirementVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/requirement/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<RequirementVo> findList(@ApiBodyObject RequirementVo vo);

    @POST
    @Path("/findByIdNotIn/")
    @ApiMethod(path = "/api/rest/requirement/findByIdNotIn/", verb = ApiVerb.POST, description = "findByIdNotIn", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<RequirementVo> findByIdNotIn(
            @ApiQueryParam(name = "ids", description = "The requirement ids") @QueryParam("ids") List<String> ids);
}
