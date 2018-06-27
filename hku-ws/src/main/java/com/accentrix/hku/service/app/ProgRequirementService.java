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

import com.accentrix.hku.vo.app.ProgRequirementVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午2:24:18 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Prog Requirement Service", description = "progRequirementService")
public interface ProgRequirementService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/progRequirement/get/", verb = ApiVerb.GET, description = "get progRequirement by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ProgRequirementVo get(
            @ApiQueryParam(name = "id", description = "The progRequirement id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/progRequirement/", verb = ApiVerb.POST, description = "save progRequirement", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ProgRequirementVo save(@ApiBodyObject ProgRequirementVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/progRequirement/save-list/", verb = ApiVerb.POST, description = "save progRequirement list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<ProgRequirementVo> save(List<ProgRequirementVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/progRequirement/{id}/", verb = ApiVerb.DELETE, description = "delete progRequirement by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The progRequirement Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/progRequirement/delete-progRequirement/", verb = ApiVerb.POST, description = "delete progRequirement", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject ProgRequirementVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/progRequirement/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ProgRequirementVo> findList();
}
