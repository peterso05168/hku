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

import com.accentrix.hku.vo.campaign.CritChinaVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 下午2:30:23 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "CritChina Service", description = "critChinaService")
public interface CritChinaService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/critChina/get/", verb = ApiVerb.GET, description = "get critChina by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    CritChinaVo get(@ApiQueryParam(name = "id", description = "The critChina id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/critChina/", verb = ApiVerb.POST, description = "save critChina", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    CritChinaVo save(@ApiBodyObject CritChinaVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/critChina/save-list/", verb = ApiVerb.POST, description = "save critChina list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<CritChinaVo> save(@ApiBodyObject List<CritChinaVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/critChina/{id}/", verb = ApiVerb.DELETE, description = "delete critChina by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The critChina Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/critChina/delete-critChina/", verb = ApiVerb.POST, description = "delete critChina", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject CritChinaVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/critChina/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<CritChinaVo> findList();

    @GET
    @Path("/findByCpgnId/")
    @ApiMethod(path = "/api/rest/critChina/findByCpgnId/", verb = ApiVerb.GET, description = "findByCpgnId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<CritChinaVo> findByCpgnId(@ApiQueryParam(name = "cpgnId") @QueryParam("cpgnId") String cpgnId);
}
