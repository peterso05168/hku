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

import com.accentrix.hku.vo.app.AcadQualNursingNrVo;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午3:52:31
 */
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "AcadQualNursingNr Service", description = "acadQualNursingNrService")
public interface AcadQualNursingNrService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/acadQualNursingNr/get/", verb = ApiVerb.GET, description = "get acadQualNursingNr by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AcadQualNursingNrVo get(
            @ApiQueryParam(name = "id", description = "The acadQualNursingNr id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/acadQualNursingNr/", verb = ApiVerb.POST, description = "save acadQualNursingNr", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AcadQualNursingNrVo save(@ApiBodyObject AcadQualNursingNrVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/acadQualNursingNr/save-list/", verb = ApiVerb.POST, description = "save acadQualNursingNr list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<AcadQualNursingNrVo> save(List<AcadQualNursingNrVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/acadQualNursingNr/{id}/", verb = ApiVerb.DELETE, description = "delete acadQualNursingNr by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The acadQualNursingNr Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/acadQualNursingNr/delete-acadQualNursingNr/", verb = ApiVerb.POST, description = "delete acadQualNursingNr", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject AcadQualNursingNrVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/acadQualNursingNr/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AcadQualNursingNrVo> findList();

    @POST
    @Path("/getByAppAcadQualNursingId/")
    @ApiMethod(path = "/api/rest/acadQualNursingNr/getByAppAcadQualNursingId/", verb = ApiVerb.POST, description = "getByAppAcadQualNursingId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AcadQualNursingNrVo> getByAppAcadQualNursingId(
            @ApiQueryParam(name = "appAcadQualNursingId") @QueryParam("appAcadQualNursingId") String appAcadQualNursingId);
}
