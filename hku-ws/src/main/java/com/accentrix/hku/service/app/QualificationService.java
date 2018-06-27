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

import com.accentrix.hku.vo.app.QualificationVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:24:42
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Qualification Service", description = "qualificationService")
public interface QualificationService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/qualification/get/", verb = ApiVerb.GET, description = "get qualification by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    QualificationVo get(@ApiQueryParam(name = "id", description = "The qualification id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/qualification/", verb = ApiVerb.POST, description = "save qualification", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    QualificationVo save(@ApiBodyObject QualificationVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/qualification/save-list/", verb = ApiVerb.POST, description = "save qualification list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<QualificationVo> save(List<QualificationVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/qualification/{id}/", verb = ApiVerb.DELETE, description = "delete qualification by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The qualification Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/qualification/delete-qualification/", verb = ApiVerb.POST, description = "delete qualification", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject QualificationVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/qualification/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<QualificationVo> findList();

    @POST
    @Path("/getByApplicationId/")
    @ApiMethod(path = "/api/rest/qualification/getByApplicationId/", verb = ApiVerb.POST, description = "getByApplicationId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<QualificationVo> getByApplicationId(
            @ApiQueryParam(name = "applicationId") @QueryParam("applicationId") String applicationId);

    @POST
    @Path("/getByApplicationIdAndExamTypeId/")
    @ApiMethod(path = "/api/rest/qualification/getByApplicationIdAndExamTypeId/", verb = ApiVerb.POST, description = "getByApplicationIdAndExamTypeId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<QualificationVo> getByApplicationIdAndExamTypeId(
            @ApiQueryParam(name = "applicationId") @QueryParam("applicationId") String applicationId,
            @ApiQueryParam(name = "examTypeId") @QueryParam("examTypeId") String examTypeId);
}
