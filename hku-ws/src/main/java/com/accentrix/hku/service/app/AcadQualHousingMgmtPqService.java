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

import com.accentrix.hku.vo.app.AcadQualHousingMgmtPqVo;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午2:26:13
 */
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "AcadQualHousingMgmtPq Service", description = "acadQualHousingMgmtPqService")
public interface AcadQualHousingMgmtPqService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/acadQualHousingMgmtPq/get/", verb = ApiVerb.GET, description = "get acadQualHousingMgmtPq by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AcadQualHousingMgmtPqVo get(
            @ApiQueryParam(name = "id", description = "The acadQualHousingMgmtPq id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/acadQualHousingMgmtPq/", verb = ApiVerb.POST, description = "save acadQualHousingMgmtPq", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AcadQualHousingMgmtPqVo save(@ApiBodyObject AcadQualHousingMgmtPqVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/acadQualHousingMgmtPq/save-list/", verb = ApiVerb.POST, description = "save acadQualHousingMgmtPq list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<AcadQualHousingMgmtPqVo> save(List<AcadQualHousingMgmtPqVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/acadQualHousingMgmtPq/{id}/", verb = ApiVerb.DELETE, description = "delete acadQualHousingMgmtPq by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The acadQualHousingMgmtPq Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/acadQualHousingMgmtPq/delete-acadQualHousingMgmtPq/", verb = ApiVerb.POST, description = "delete acadQualHousingMgmtPq", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject AcadQualHousingMgmtPqVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/acadQualHousingMgmtPq/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AcadQualHousingMgmtPqVo> findList();

    @POST
    @Path("/getByAppAcadQualHousingMgmtId/")
    @ApiMethod(path = "/api/rest/acadQualHousingMgmtPq/getByAppAcadQualHousingMgmtId/", verb = ApiVerb.POST, description = "getByAppAcadQualHousingMgmtId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AcadQualHousingMgmtPqVo> getByAppAcadQualHousingMgmtId(
            @ApiQueryParam(name = "appAcadQualHousingMgmtId") @QueryParam("appAcadQualHousingMgmtId") String appAcadQualHousingMgmtId);
}
