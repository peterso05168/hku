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

import com.accentrix.hku.vo.app.ReqDocVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午2:26:12 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "ReqDoc Service", description = "reqDocService")
public interface ReqDocService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/reqDoc/get/", verb = ApiVerb.GET, description = "get reqDoc by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ReqDocVo get(@ApiQueryParam(name = "id", description = "The reqDoc id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/reqDoc/", verb = ApiVerb.POST, description = "save reqDoc", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ReqDocVo save(@ApiBodyObject ReqDocVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/reqDoc/save-list/", verb = ApiVerb.POST, description = "save reqDoc list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<ReqDocVo> save(List<ReqDocVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/reqDoc/{id}/", verb = ApiVerb.DELETE, description = "delete reqDoc by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The reqDoc Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/reqDoc/delete-reqDoc/", verb = ApiVerb.POST, description = "delete reqDoc", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject ReqDocVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/reqDoc/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ReqDocVo> findList();

    @POST
    @Path("/findByReqDocType/")
    @ApiMethod(path = "/api/rest/reqDoc/findByReqDocType/", verb = ApiVerb.POST, description = "findByReqDocType", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ReqDocVo> findByReqDocType(
            @ApiQueryParam(name = "applicationId", description = "The application id") @QueryParam("applicationId") String applicationId,
            @ApiQueryParam(name = "reqDocType", description = "The reqDoc type") @QueryParam("reqDocType") String reqDocType);

    @GET
    @Path("getByApplicationIdAndReqDocConfIdAndQualificationId")
    @ApiMethod(path = "/api/rest/reqDoc/getByApplicationIdAndReqDocConfIdAndQualificationId/", verb = ApiVerb.GET, description = "get reqDoc by appId,docType,docCd,docName", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ReqDocVo getByApplicationIdAndReqDocConfIdAndQualificationId(
            @ApiQueryParam(name = "applicationId", description = "The application id") @QueryParam("applicationId") String applicationId,
            @ApiQueryParam(name = "reqDocConfId", description = "The reqDocConf id") @QueryParam("reqDocConfId") String reqDocConfId,
            @ApiQueryParam(name = "qualificationId", description = "The qualification id") @QueryParam("qualificationId") String qualificationId);
}
