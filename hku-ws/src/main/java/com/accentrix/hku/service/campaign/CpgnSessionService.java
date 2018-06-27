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

import com.accentrix.hku.vo.campaign.CpgnSessionVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 下午2:29:57 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "CpgnSession Service", description = "cpgnSessionService")
public interface CpgnSessionService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/cpgnSession/get/", verb = ApiVerb.GET, description = "get cpgnSession by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    CpgnSessionVo get(@ApiQueryParam(name = "id", description = "The cpgnSession id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/cpgnSession/", verb = ApiVerb.POST, description = "save cpgnSession", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    CpgnSessionVo save(@ApiBodyObject CpgnSessionVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/cpgnSession/save-list/", verb = ApiVerb.POST, description = "save cpgnSession list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<CpgnSessionVo> save(@ApiBodyObject List<CpgnSessionVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/cpgnSession/{id}/", verb = ApiVerb.DELETE, description = "delete cpgnSession by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The cpgnSession Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/cpgnSession/delete-cpgnSession/", verb = ApiVerb.POST, description = "delete cpgnSession", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject CpgnSessionVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/cpgnSession/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<CpgnSessionVo> findList();

    @GET
    @Path("/findByCentreId/")
    @ApiMethod(path = "/api/rest/cpgnSession/findByCentreId/", verb = ApiVerb.GET, description = "findByCentreId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<CpgnSessionVo> findByCentreId(
            @ApiQueryParam(name = "centreId", required = false) @QueryParam("centreId") String centreId);
}
