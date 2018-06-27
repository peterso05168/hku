package com.accentrix.hku.service.campaign;

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

import com.accentrix.hku.vo.campaign.CritChinaDtlVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 下午2:30:45 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "CritChinaDtl Service", description = "critChinaDtlService")
public interface CritChinaDtlService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/critChinaDtl/get/", verb = ApiVerb.GET, description = "get critChinaDtl by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    CritChinaDtlVo get(@ApiQueryParam(name = "id", description = "The critChinaDtl id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/critChinaDtl/", verb = ApiVerb.POST, description = "save critChinaDtl", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    CritChinaDtlVo save(@ApiBodyObject CritChinaDtlVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/critChinaDtl/save-list/", verb = ApiVerb.POST, description = "save critChinaDtl list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<CritChinaDtlVo> save(@ApiBodyObject List<CritChinaDtlVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/critChinaDtl/{id}/", verb = ApiVerb.DELETE, description = "delete critChinaDtl by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The critChinaDtl Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/critChinaDtl/delete-critChinaDtl/", verb = ApiVerb.POST, description = "delete critChinaDtl", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject CritChinaDtlVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/critChinaDtl/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<CritChinaDtlVo> findList();

    @GET
    @Path("/findByCritChinaId/")
    @ApiMethod(path = "/api/rest/critChinaDtl/findByCritChinaId/", verb = ApiVerb.GET, description = "findByCritChinaId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<CritChinaDtlVo> findByCritChinaId(
            @ApiQueryParam(name = "cpgnChinaId", required = false) @QueryParam("cpgnChinaId") String cpgnChinaId);
}
