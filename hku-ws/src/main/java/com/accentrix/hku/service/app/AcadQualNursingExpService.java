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

import com.accentrix.hku.vo.app.AcadQualNursingExpVo;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午3:21:06
 */
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "AcadQualNursingExp Service", description = "acadQualNursingExpService")
public interface AcadQualNursingExpService {
    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/acadQualNursingExp/get/", verb = ApiVerb.GET, description = "get acadQualNursingExp by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AcadQualNursingExpVo get(
            @ApiQueryParam(name = "id", description = "The acadQualNursingExp id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/acadQualNursingExp/", verb = ApiVerb.POST, description = "save acadQualNursingExp", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AcadQualNursingExpVo save(@ApiBodyObject AcadQualNursingExpVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/acadQualNursingExp/save-list/", verb = ApiVerb.POST, description = "save acadQualNursingExp list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<AcadQualNursingExpVo> save(List<AcadQualNursingExpVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/acadQualNursingExp/{id}/", verb = ApiVerb.DELETE, description = "delete acadQualNursingExp by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The acadQualNursingExp Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/acadQualNursingExp/delete-acadQualNursingExp/", verb = ApiVerb.POST, description = "delete acadQualNursingExp", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject AcadQualNursingExpVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/acadQualNursingExp/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AcadQualNursingExpVo> findList();

    @POST
    @Path("/getByAppAcadQualNursingId/")
    @ApiMethod(path = "/api/rest/acadQualNursingExp/getByAppAcadQualNursingId/", verb = ApiVerb.POST, description = "getByAppAcadQualNursingId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AcadQualNursingExpVo> getByAppAcadQualNursingId(
            @ApiQueryParam(name = "appAcadQualNursingId") @QueryParam("appAcadQualNursingId") String appAcadQualNursingId);

    @POST
    @Path("/getByAppAcadQualNursingIdAndType/")
    @ApiMethod(path = "/api/rest/acadQualNursingExp/getByAppAcadQualNursingIdAndType/", verb = ApiVerb.POST, description = "getByAppAcadQualNursingId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AcadQualNursingExpVo> getByAppAcadQualNursingIdAndType(
            @ApiQueryParam(name = "appAcadQualNursingId") @QueryParam("appAcadQualNursingId") String appAcadQualNursingId,
            @ApiQueryParam(name = "type") @QueryParam("type") String type);
}
