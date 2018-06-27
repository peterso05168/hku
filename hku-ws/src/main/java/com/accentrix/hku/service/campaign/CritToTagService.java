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

import com.accentrix.hku.vo.campaign.CritToTagVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 下午2:31:45 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "critToTag Service", description = "critToTagService")
public interface CritToTagService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/critToTag/get/", verb = ApiVerb.GET, description = "get critToTag by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    CritToTagVo get(@ApiQueryParam(name = "id", description = "The critToTag id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/critToTag/", verb = ApiVerb.POST, description = "save critToTag", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    CritToTagVo save(@ApiBodyObject CritToTagVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/critToTag/save-list/", verb = ApiVerb.POST, description = "save critToTag list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<CritToTagVo> save(@ApiBodyObject List<CritToTagVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/critToTag/{id}/", verb = ApiVerb.DELETE, description = "delete critToTag by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The critToTag Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/critToTag/delete-critToTag/", verb = ApiVerb.POST, description = "delete critToTag", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject CritToTagVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/critToTag/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<CritToTagVo> findList();

    @POST
    @Path("/findByCrit/")
    @ApiMethod(path = "/api/rest/critToTag/findByCrit/", verb = ApiVerb.POST, description = "findByCrit", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<String> findByCrit(@ApiQueryParam(name = "critId", required = false) @QueryParam("critId") String critId,
            @ApiQueryParam(name = "countryId", required = false) @QueryParam("type") String type);

    @POST
    @Path("/findByCritToList/")
    @ApiMethod(path = "/api/rest/critToTag/findByCritToList/", verb = ApiVerb.POST, description = "findByCritToList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<CritToTagVo> findByCritToList(
            @ApiQueryParam(name = "critId", required = false) @QueryParam("critId") String critId,
            @ApiQueryParam(name = "countryId", required = false) @QueryParam("type") String type);
}
