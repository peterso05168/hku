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

import com.accentrix.hku.vo.app.AcadQualHousingMgmtRweVo;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午2:55:22
 */
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "AcadQualHousingMgmtRwe Service", description = "acadQualHousingMgmtRweService")
public interface AcadQualHousingMgmtRweService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/acadQualHousingMgmtRwe/get/", verb = ApiVerb.GET, description = "get acadQualHousingMgmtRwe by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AcadQualHousingMgmtRweVo get(
            @ApiQueryParam(name = "id", description = "The acadQualHousingMgmtRwe id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/acadQualHousingMgmtRwe/", verb = ApiVerb.POST, description = "save acadQualHousingMgmtRwe", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AcadQualHousingMgmtRweVo save(@ApiBodyObject AcadQualHousingMgmtRweVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/acadQualHousingMgmtRwe/save-list/", verb = ApiVerb.POST, description = "save acadQualHousingMgmtRwe list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<AcadQualHousingMgmtRweVo> save(List<AcadQualHousingMgmtRweVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/acadQualHousingMgmtRwe/{id}/", verb = ApiVerb.DELETE, description = "delete acadQualHousingMgmtRwe by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The acadQualHousingMgmtRwe Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/acadQualHousingMgmtRwe/delete-acadQualHousingMgmtRwe/", verb = ApiVerb.POST, description = "delete acadQualHousingMgmtRwe", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject AcadQualHousingMgmtRweVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/acadQualHousingMgmtRwe/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AcadQualHousingMgmtRweVo> findList();

    @POST
    @Path("/getByAppAcadQualHousingMgmtId/")
    @ApiMethod(path = "/api/rest/acadQualHousingMgmtRwe/getByAppAcadQualHousingMgmtId/", verb = ApiVerb.POST, description = "getByAppAcadQualHousingMgmtId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AcadQualHousingMgmtRweVo> getByAppAcadQualHousingMgmtId(
            @ApiQueryParam(name = "appAcadQualHousingMgmtId") @QueryParam("appAcadQualHousingMgmtId") String appAcadQualHousingMgmtId);
}
