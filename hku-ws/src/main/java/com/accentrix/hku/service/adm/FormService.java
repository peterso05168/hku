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

import com.accentrix.hku.vo.adm.FormVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:18:08
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Form Service", description = "formService")
public interface FormService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/form/get/", verb = ApiVerb.GET, description = "get form by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    FormVo get(@ApiQueryParam(name = "id", description = "The form id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/form/", verb = ApiVerb.POST, description = "save form", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    FormVo save(@ApiBodyObject FormVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/form/save-list/", verb = ApiVerb.POST, description = "save form list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<FormVo> save(List<FormVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/form/{id}/", verb = ApiVerb.DELETE, description = "delete form by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The form Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/form/delete-form/", verb = ApiVerb.POST, description = "delete form", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject FormVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/form/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<FormVo> findList();

    @POST
    @Path("/getByAdmExeId/")
    @ApiMethod(path = "/api/rest/form/getByAdmExeId/", verb = ApiVerb.POST, description = "getByAdmExeId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    FormVo getByAdmExeId(@ApiQueryParam(name = "admExeId") @QueryParam("admExeId") String admExeId);
}
