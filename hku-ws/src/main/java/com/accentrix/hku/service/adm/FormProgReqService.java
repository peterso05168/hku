package com.accentrix.hku.service.adm;

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

import com.accentrix.hku.vo.adm.FormProgReqVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午3:12:25 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Form Prog Req Service", description = "formProgReqService")
public interface FormProgReqService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/formProgReq/get/", verb = ApiVerb.GET, description = "get formProgReq by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    FormProgReqVo get(@ApiQueryParam(name = "id", description = "The formProgReq id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/formProgReq/", verb = ApiVerb.POST, description = "save formProgReq", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    FormProgReqVo save(@ApiBodyObject FormProgReqVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/formProgReq/save-list/", verb = ApiVerb.POST, description = "save formProgReq list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<FormProgReqVo> save(List<FormProgReqVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/formProgReq/{id}/", verb = ApiVerb.DELETE, description = "delete formProgReq by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The formProgReq Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/formProgReq/delete-formProgReq/", verb = ApiVerb.POST, description = "delete formProgReq", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject FormProgReqVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/formProgReq/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<FormProgReqVo> findList();

    @GET
    @Path("getByFormProgId")
    @ApiMethod(path = "/api/rest/formProgReq/getByFormProgId/", verb = ApiVerb.GET, description = "get by form prog id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<FormProgReqVo> getByFormProgId(
            @ApiQueryParam(name = "formProgId") @QueryParam("formProgId") String formProgId);

    @GET
    @Path("getByRequirementId")
    @ApiMethod(path = "/api/rest/formProgReq/getByRequirementId/", verb = ApiVerb.GET, description = "get by requirement id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<FormProgReqVo> getByRequirementId(
            @ApiQueryParam(name = "requirementId") @QueryParam("requirementId") String requirementId);
}
