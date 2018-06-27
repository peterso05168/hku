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

import com.accentrix.hku.vo.app.AcadQualNursingPrqVo;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午4:16:47
 */
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "AcadQualNursingPrq Service", description = "AcadQualNursingPrqService")
public interface AcadQualNursingPrqService {
    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/acadQualNursingPrq/get/", verb = ApiVerb.GET, description = "get acadQualNursingPrq by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AcadQualNursingPrqVo get(
            @ApiQueryParam(name = "id", description = "The acadQualNursingPrq id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/acadQualNursingPrq/", verb = ApiVerb.POST, description = "save acadQualNursingPrq", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AcadQualNursingPrqVo save(@ApiBodyObject AcadQualNursingPrqVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/acadQualNursingPrq/save-list/", verb = ApiVerb.POST, description = "save acadQualNursingPrq list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<AcadQualNursingPrqVo> save(List<AcadQualNursingPrqVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/acadQualNursingPrq/{id}/", verb = ApiVerb.DELETE, description = "delete acadQualNursingPrq by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The acadQualNursingPrq Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/acadQualNursingPrq/delete-acadQualNursingPrq/", verb = ApiVerb.POST, description = "delete acadQualNursingPrq", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject AcadQualNursingPrqVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/acadQualNursingPrq/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AcadQualNursingPrqVo> findList();

    @POST
    @Path("/getByAppAcadQualNursingId/")
    @ApiMethod(path = "/api/rest/acadQualNursingPrq/getByAppAcadQualNursingId/", verb = ApiVerb.POST, description = "getByAppAcadQualNursingId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AcadQualNursingPrqVo> getByAppAcadQualNursingId(
            @ApiQueryParam(name = "appAcadQualNursingId") @QueryParam("appAcadQualNursingId") String appAcadQualNursingId);
}
